package com.companyInterview.wise;

import java.util.*;

public class OrderExecution {

}

//Order - 1000 Euro
//Account 1 - 10 Euro
//Account 2 - 100 Euro
//Account 4 - 10 USD
//Account 3 - 900 Euro


//TODO :: CODE HAVE NOT VERIFIED
//Copied it from CLAUDE
class OrderExecutionService {

    OrderRepository orderRepository;
    AccountRepository accountRepository;

    //TODO :: Write the logic in this method
    public void executeOrders(Long customerId) {
        List<Order> orders = orderRepository.getOrders(customerId);
        List<Account> accounts = accountRepository.getAccounts(customerId);

        // OPTIMIZATION: group accounts by currency once upfront.
        // Without this, every order would re-scan all accounts -> O(orders x accounts).
        // With this, lookup per order is O(1) -> overall O(accounts + orders).
        Map<String, List<Account>> accountsByCurrency = groupByCurrency(accounts);

        for (Order order : orders) {
            // Skip orders already executed (idempotency - safe to re-run this job daily)
            if (order.state == States.EXECUTED) {
                continue;
            }

            List<Account> sameCurrency = accountsByCurrency.get(order.currency);
            if (sameCurrency == null) {
                sameCurrency = new ArrayList<>();
            }
            double sameCurrencyTotal = sumBalances(sameCurrency);

            // HAPPY PATH: same-currency accounts alone cover the order.
            // No conversion needed -> no conversion loss, cheapest case.
            if (sameCurrencyTotal >= order.amount) {
                debitAccounts(sameCurrency, order.amount);
                for (Account account : sameCurrency) {
                    accountRepository.save(account);
                }

                order.execute();
                order.state = States.EXECUTED;
                orderRepository.save(order);

            } else {
                // SMART CONVERSION PATH: same-currency isn't enough on its own.
                // Check if the order can be covered using ALL accounts combined (any currency).
                double total = sumBalances(accounts);

                if (total >= order.amount) {
                    double remaining = order.amount;

                    // Step 1: use up same-currency funds first (no conversion loss)
                    double debited = debitAccounts(sameCurrency, remaining);
                    remaining -= debited;
                    for (Account account : sameCurrency) {
                        accountRepository.save(account);
                    }

                    // Step 2: cover the shortfall from other-currency accounts
                    for (Account account : accounts) {
                        if (remaining <= 0) break;
                        // Skip accounts already handled in step 1
                        // (reference equality, not equals() - see containsByReference comment below)
                        if (containsByReference(sameCurrency, account)) continue;

                        double deduct = Math.min(account.balance, remaining);
                        account.balance -= deduct;
                        remaining -= deduct;
                        accountRepository.save(account);
                    }

                    order.execute();
                    order.state = States.EXECUTED;
                    orderRepository.save(order);
                }
                // else: insufficient funds across ALL accounts/currencies.
                // Order stays PENDING - executeOrders runs daily, so it'll retry tomorrow.
            }
        }
    }

    // Buckets accounts by currency so executeOrders can look them up in O(1) per order
    private Map<String, List<Account>> groupByCurrency(List<Account> accounts) {
        Map<String, List<Account>> result = new HashMap<>();
        for (Account account : accounts) {
            List<Account> bucket = result.get(account.currency);
            if (bucket == null) {
                bucket = new ArrayList<>();
                result.put(account.currency, bucket);
            }
            bucket.add(account);
        }
        return result;
    }

    // Debits up to `amount` across a list of accounts (mutates balances in place).
    // Returns the amount actually debited, since accounts may not fully cover `amount`.
    private double debitAccounts(List<Account> accounts, double amount) {
        double remaining = amount;
        for (Account account : accounts) {
            if (remaining <= 0) break;
            double deduct = Math.min(account.balance, remaining);
            account.balance -= deduct;
            remaining -= deduct;
        }
        return amount - remaining;
    }

    private double sumBalances(List<Account> accounts) {
        double total = 0;
        for (Account account : accounts) {
            total += account.balance;
        }
        return total;
    }

    // IMPORTANT: uses == (reference equality), not .equals().
    // This deliberately avoids depending on whether Account ever gets a
    // value-based equals()/hashCode() override - identity is what we actually mean here.
    private boolean containsByReference(List<Account> list, Account target) {
        for (Account account : list) {
            if (account == target) {
                return true;
            }
        }
        return false;
    }
}

interface OrderRepository {
    List<Order> getOrders(Long customerId);

    void save(Order order);
}

interface AccountRepository {
    Account getAccount(Long customerId);

    List<Account> getAccounts(Long customerId);

    void save(Account account);
}

enum States {
    PENDING, EXECUTED
}

class Order {
    double amount;
    String currency;
    States state;

    //Assume this method is implemented
    void execute() { /* perform transfer */ }
}

class Account {
    String currency;
    double balance;
}

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
        var orders = orderRepository.getOrders(customerId);
        var accounts = accountRepository.getAccounts(customerId);

        for (var order : orders) {
            if (order.state == States.EXECUTED) {
                continue;
            }

            // Find same-currency account first
            Account primary = findAccount(accounts, order.currency);

            if (primary != null && primary.balance >= order.amount) {
                // Happy path: same-currency account has enough
                primary.balance -= order.amount;
                order.execute();
                order.state = States.EXECUTED;
                orderRepository.save(order);
                accountRepository.save(primary);

            } else {
                // Smart conversion: combine balances across all accounts
                double total = accounts.stream().mapToDouble(a -> a.balance).sum();

                if (total >= order.amount) {
                    double remaining = order.amount;

                    // Drain same-currency account first
                    if (primary != null && primary.balance > 0) {
                        remaining -= primary.balance;
                        primary.balance = 0;
                        accountRepository.save(primary);
                    }

                    // Drain other accounts to cover the rest
                    for (Account account : accounts) {
                        if (remaining <= 0) break;
                        if (account == primary) continue;

                        double deduct = Math.min(account.balance, remaining);
                        account.balance -= deduct;
                        remaining -= deduct;
                        accountRepository.save(account);
                    }

                    order.execute();
                    order.state = States.EXECUTED;
                    orderRepository.save(order);
                }
                // else: insufficient funds — order stays PENDING, retried tomorrow
            }
        }
    }

    private Account findAccount(List<Account> accounts, String currency) {
        return accounts.stream()
                .filter(a -> a.currency.equals(currency))
                .findFirst()
                .orElse(null);
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

package com.random;

public class AddDigits {

    public static void main(String[] args) {
        AddDigits addDigits = new AddDigits();
        int num = 38;
        int result = addDigits.addDigits(num);
        System.out.println(result);
    }

    public int addDigits(int num) {

        String s = String.valueOf(num);
        while (s.length() != 1) {
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                int ind = s.charAt(i) - '0';
                sum = sum + ind;
            }
            s = String.valueOf(sum);
        }

        return Integer.parseInt(s);

    }
}

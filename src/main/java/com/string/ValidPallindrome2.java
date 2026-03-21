package com.string;

public class ValidPallindrome2 {

    public static void main(String[] args) {
        ValidPallindrome2 obj = new ValidPallindrome2();
        String s = "abca";
        boolean res = obj.validPalindrome(s, 0, s.length() - 1, 0);
        System.out.println(res);
    }

    boolean validPalindrome(String str, int s, int e, int deleted) {
        if (deleted >= 2) {
            return false;
        }
        if (s >= e) {
            return true;
        }

        if (str.charAt(s) == str.charAt(e)) {
            return validPalindrome(str, s + 1, e - 1, deleted);
        } else {
            return validPalindrome(str, s + 1, e, deleted + 1) || validPalindrome(str, s, e - 1, deleted + 1);
        }

    }
}

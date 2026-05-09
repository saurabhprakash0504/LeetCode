package com.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationsOfAPhoneNumber obj = new LetterCombinationsOfAPhoneNumber();
        List<String> res = obj.letterCombinations(digits);
        System.out.println(res);
    }

    public List<String> letterCombinations(String digits) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> al = new ArrayList<>();

        find(map, digits, 0, "", al);

        return al;

    }

    void find(HashMap<Integer, String> map, String digits, int ind, String temp, List<String> al) {

        if (ind == digits.length()) {
            al.add(new String(temp));
            return;
        }

        char s = digits.charAt(ind);
        int d = s - '0';
        String val = map.get(d);
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            temp = temp + c;
            find(map, digits, ind + 1, temp, al);
            temp = temp.substring(0, temp.length() - 1);
        }

    }
}

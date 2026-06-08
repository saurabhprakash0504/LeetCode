package com.string;

import java.util.HashMap;

public class IsIsomorphic {

    public static void main(String[] args) {
        IsIsomorphic obj = new IsIsomorphic();
        String s = "egg";
        String t = "add";
        boolean res = obj.isIsomorphic(s, t);
        System.out.println("res >> " + res);
    }

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> rev = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            if (map.containsKey(c)) {
                char temp = map.get(c);
                if (temp != d) {
                    return false;
                }

            } else {
                map.put(c, d);
            }


            if (rev.containsKey(d)) {
                char temp = rev.get(d);
                if (temp != c) {
                    return false;
                }

            } else {
                rev.put(d, c);
            }
        }
        return true;
    }
}

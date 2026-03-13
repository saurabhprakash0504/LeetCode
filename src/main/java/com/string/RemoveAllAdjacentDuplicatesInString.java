package com.string;

public class RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {

        String s = "abbaca";
        String res = new RemoveAllAdjacentDuplicatesInString().removeDuplicates(s);
        System.out.println("res >> " + res);
    }

    public String removeDuplicates(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() == 0) {
                sb.append(s.charAt(i));
            } else {
                char prev = sb.charAt(sb.length() - 1);
                char curr = s.charAt(i);
                if (prev == curr) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(curr);
                }
            }
        }

        return sb.toString();
    }
}

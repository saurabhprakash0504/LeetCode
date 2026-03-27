package com.random;

public class BasicCalculator2 {

    public static void main(String[] args) {

        BasicCalculator2 obj = new BasicCalculator2();
        String s = " 3+5 / 2 ";
        int res = obj.calculate2(s);
        System.out.println(res);
    }


    public int calculate2(String s) {

        int prevVal = 0;
        char prevSign = '+';
        int res = 0;
        int ind = 0;
        while (ind < s.length()) {
            Character c = s.charAt(ind);
            if (Character.isDigit(c)) {

                int curr = 0;
                while (ind < s.length() && Character.isDigit(s.charAt(ind))) {
                    curr = curr * 10 + (s.charAt(ind) - '0');
                    ind++;
                }

                switch (prevSign) {

                    case '+':
                        res = res + curr;
                        prevVal = curr;
                        break;
                    case '-':
                        res = res - curr;
                        //check here prevVal = -curr
                        prevVal = -curr;
                        break;
                    case '*':
                        res = res - prevVal;
                        res = res + (prevVal * curr);
                        //check here prevVal = prevVal * curr
                        prevVal = prevVal * curr;
                        break;
                    case '/':
                        res = res - prevVal;
                        res = res + prevVal / curr;
                        //check here prevVal = prevVal / curr
                        prevVal = prevVal / curr;
                        break;
                }
            } else if (c == ' ') {
                ind++;
            } else {
                prevSign = c;
                ind++;
            }
        }

        return res;

    }

}

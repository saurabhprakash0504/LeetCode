package com.string;

public class StringToIntegerATOI {

    public static void main(String[] args) {
        StringToIntegerATOI solution = new StringToIntegerATOI();
        String str = "   -42";
        int result = solution.myAtoi(str);
        System.out.println(result); // Output: -42
    }

    public int myAtoi(String str) {

        char[] arr = str.toCharArray();
        int s = 0;
        //whitespace
        while (s < arr.length && arr[s] == ' ') {
            s++;
        }

        // sym
        boolean sym = false;
        if (s < arr.length && arr[s] == '-') {
            sym = true;
            s++;
        } else if (s < arr.length && arr[s] == '+') {
            s++;
        }

        //zero
        while (s < arr.length && arr[s] == '0') {
            s++;
        }

        StringBuffer sb = new StringBuffer();
        while (s < arr.length && arr[s] >= '0' && arr[s] <= '9') {
            if (sb.length() >= 10) {
                return sym ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sb = sb.append(arr[s]);
            s++;
        }

        System.out.println("sb >> " + sb);

        if (sb.length() > 0) {
            if (sym) {
                sb = sb.insert(0, '-');
            }

            Long val = Long.valueOf(String.valueOf(sb));
            //  BigInteger val = new BigInteger(String.valueOf(sb));

            if (val > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (val < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.valueOf(String.valueOf(sb));
            }

        } else {
            return 0;
        }

    }
}

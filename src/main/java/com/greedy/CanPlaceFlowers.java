package com.greedy;

public class CanPlaceFlowers {

    public static void main(String[] args) {

    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            boolean leftEmpty  = (i == 0) || (flowerbed[i - 1] == 0);
            boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

            if (flowerbed[i] == 0 && leftEmpty && rightEmpty) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n <= 0;
    }
}

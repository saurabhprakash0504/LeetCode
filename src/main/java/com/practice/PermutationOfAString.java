package com.practice;

import java.util.ArrayList;

public class PermutationOfAString {

    public static void main(String[] args) {
        PermutationOfAString ps = new PermutationOfAString();
        ArrayList<String> permutations = ps.getPermutations("abc");
        System.out.println(permutations);
    }

    /**
     * Returns all permutations of the given string.
     */
    public ArrayList<String> getPermutations(String input) {

        ArrayList<String> result = new ArrayList<>();
        char[] chars = input.toCharArray();

        generate(chars, 0, result);
        return result;
    }

    /**
     * Recursively generates permutations by fixing one position at a time.
     */
    private void generate(char[] chars, int fixedIndex, ArrayList<String> result) {

        // Base case: all positions fixed → we have a complete permutation
        if (fixedIndex == chars.length) {
            result.add(String.valueOf(chars));
            return;
        }

        // Try each index as a candidate to swap into the fixed position
        for (int swapIndex = fixedIndex; swapIndex < chars.length; swapIndex++) {

            // Swap the candidate into the fixed position
            swap(chars, swapIndex, fixedIndex);

            // Recurse to fix the next position
            generate(chars, fixedIndex + 1, result);

            // Backtrack: restore original order
            swap(chars, swapIndex, fixedIndex);
        }
    }

    /**
     * Swaps two characters in the array.
     */
    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}


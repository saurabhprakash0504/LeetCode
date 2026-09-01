package com.karat;

/**

You are going on a camping trip, but before you leave you need to buy groceries. To optimize your time spent in the store, instead of buying the items from your shopping list in order, you plan to buy everything you need from one department before moving to the next.




Given an unsorted list of products with their departments and a shopping list, return the time saved in terms of the number of department visits eliminated.




Example:

products = [

["Cheese", "Dairy"],

["Carrots", "Produce"],

["Potatoes", "Produce"],

["Canned Tuna", "Pantry"],

["Romaine Lettuce", "Produce"],

["Chocolate Milk", "Dairy"],

["Flour", "Pantry"],

["Iceberg Lettuce", "Produce"],

["Coffee", "Pantry"],

["Pasta", "Pantry"],

["Milk", "Dairy"],

["Blueberries", "Produce"],

["Pasta Sauce", "Pantry"]

]




list1 = ["Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"]




For example, buying the items from list1 in order would take 5 department visits, whereas your method would lead to only visiting 3 departments, a difference of 2 departments.




Produce(Blueberries)->Dairy(Milk)->Pantry(Coffee/Flour)->Dairy(Cheese)->Produce(Carrots) = 5 department visits

New: Produce(Blueberries/Carrots)->Pantry(Coffee/Flour)->Dairy(Milk/Cheese) = 3 department visits




list2 = ["Blueberries", "Carrots", "Coffee", "Milk", "Flour", "Cheese"] => 2

list3 = ["Blueberries", "Carrots", "Romaine Lettuce", "Iceberg Lettuce"] => 0

list4 = ["Milk", "Flour", "Chocolate Milk", "Pasta Sauce"] => 2

list5 = ["Cheese", "Potatoes", "Blueberries", "Canned Tuna"] => 0




All Test Cases:

shopping(products, list1) => 2

shopping(products, list2) => 2

shopping(products, list3) => 0

shopping(products, list4) => 2

shopping(products, list5) => 0




Complexity Variable:

n: number of products

*/

import java.io.*;

import java.util.*;

//import javafx.util.Pair;


public class ShoppingCart {

    public static void main(String[] argv) {

        String[][] products = {

                {"Cheese", "Dairy"},

                {"Carrots", "Produce"},

                {"Potatoes", "Produce"},

                {"Canned Tuna", "Pantry"},

                {"Romaine Lettuce", "Produce"},

                {"Chocolate Milk", "Dairy"},

                {"Flour", "Pantry"},

                {"Iceberg Lettuce", "Produce"},

                {"Coffee", "Pantry"},

                {"Pasta", "Pantry"},

                {"Milk", "Dairy"},

                {"Blueberries", "Produce"},

                {"Pasta Sauce", "Pantry"}

        };


        String[] list1 = {"Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"};

        String[] list2 = {"Blueberries", "Carrots", "Coffee", "Milk", "Flour", "Cheese"};

        String[] list3 = {"Blueberries", "Carrots", "Romaine Lettuce", "Iceberg Lettuce"};

        String[] list4 = {"Milk", "Flour", "Chocolate Milk", "Pasta Sauce"};

        String[] list5 = {"Cheese", "Potatoes", "Blueberries", "Canned Tuna"};


        System.out.println(shopping(products, list1)); //=> 2

        System.out.println(shopping(products, list2)); //=>2

        System.out.println(shopping(products, list3)); // =>0

        System.out.println(shopping(products, list4)); // => 2

        System.out.println(shopping(products, list5)); //=> 0

    }
    //TODO: Implement the shopping method below
    static int shopping(String[][] mat, String[] list1) {

        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < mat.length; i++) {

            map.put(mat[i][0], mat[i][1]);

        }


        int first = findInitialCount(map, list1);

        int sec = findLeastDepartToVisit(map, list1);

        return first - sec;

    }

    static int findInitialCount(HashMap<String, String> map, String[] list1) {

        int count = 0;

        String prevDep = "";

        for (String s : list1) {

            if (!map.get(s).equals(prevDep)) {

                count++;

                prevDep = map.get(s);

            }

        }

        return count;

    }


    static int findLeastDepartToVisit(HashMap<String, String> map, String[] list1) {


        HashSet<String> set = new HashSet<String>();

        for (String s : list1) {

            set.add(map.get(s));

        }

        return set.size();

    }


}


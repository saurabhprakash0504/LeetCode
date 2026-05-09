package com.multithreading;

public class ThreeThreadPrint1to30 {

    private int num = 1;
    private final int MAX = 30;

    public static void main(String[] args) {
        ThreeThreadPrint1to30 obj = new ThreeThreadPrint1to30();

        Thread t1 = new Thread(() -> obj.printNumbers(1), "Thread-1");
        Thread t2 = new Thread(() -> obj.printNumbers(2), "Thread-2");
        Thread t3 = new Thread(() -> obj.printNumbers(0), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }

    public synchronized void printNumbers(int threadId) {
        while (num <= MAX) {

            // Check if it's this thread's turn
            while (num % 3 != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num > MAX) {
                    return;
                }
            }

            System.out.println(Thread.currentThread().getName() + " -> " + num);
            num++;

            notifyAll();
        }
    }

}

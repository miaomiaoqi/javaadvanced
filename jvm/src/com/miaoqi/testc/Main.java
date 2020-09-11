package com.miaoqi.testc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.next();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                }
            }
        }, "while true").start();
        sc.next();
        testWait(new Object());
    }

    private static void testWait(Object obj) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "wait").start();
    }

}

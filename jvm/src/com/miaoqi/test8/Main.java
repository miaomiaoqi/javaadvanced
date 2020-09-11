package com.miaoqi.test8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// -XX:PretenureSizeThreshold=10M
public class Main {

    private static final int M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] b1 = new byte[7 * M];
        Lock lock = new ReentrantLock();
    }
}

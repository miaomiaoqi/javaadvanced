package com.miaoqi.juc.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConditionABC {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printC();
            }
        }).start();

    }

}

class ShareData {

    private int number = 1;

    private Lock lock = new ReentrantLock();
    Condition condition1 = this.lock.newCondition();
    Condition condition2 = this.lock.newCondition();
    Condition condition3 = this.lock.newCondition();


    public void printA() {
        try {
            this.lock.lock();
            while (this.number != 1) {
                this.condition1.await();
            }
            System.out.println("A");
            this.number = 2;
            this.condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void printB() {
        try {
            this.lock.lock();
            while (this.number != 2) {
                this.condition2.await();
            }
            System.out.println("B");
            this.number = 3;
            this.condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void printC() {
        try {
            this.lock.lock();
            while (this.number != 3) {
                this.condition3.await();
            }
            System.out.println("C");
            this.number = 1;
            this.condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

}



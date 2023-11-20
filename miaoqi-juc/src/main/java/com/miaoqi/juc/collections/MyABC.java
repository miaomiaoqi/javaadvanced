package com.miaoqi.juc.collections;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miaoqi
 * @date 2017-10-30 下午5:14
 **/
public class MyABC {

    public static void main(String[] args) {

        MyABCLoop2 loop = new MyABCLoop2();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                loop.loopA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                loop.loopB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                loop.loopC();
            }
        }).start();
    }

}

class MyABCLoop1 {

    private Integer num = 1;

    public synchronized void loopA() {
        while (num != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("A");
        num = 2;
        this.notifyAll();
    }

    public synchronized void loopB() {
        while (num != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("B");
        num = 3;
        this.notifyAll();
    }

    public synchronized void loopC() {
        while (num != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("C");
        num = 1;
        this.notifyAll();
    }
}

class MyABCLoop2 {

    private Integer num = 1;

    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void loopA() {
        try {
            lock.lock();
            if (num != 1) {
                conditionA.await();
            }
            System.out.print("A");
            num = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        try {
            lock.lock();
            if (num != 2) {
                conditionB.await();
            }
            System.out.print("B");
            num = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        try {
            lock.lock();
            if (num != 3) {
                conditionC.await();
            }
            System.out.print("C");
            num = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

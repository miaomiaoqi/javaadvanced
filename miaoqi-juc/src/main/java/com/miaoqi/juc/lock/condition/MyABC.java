package com.miaoqi.juc.lock.condition;

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
        while (this.num != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("A");
        this.num = 2;
        this.notifyAll();
    }

    public synchronized void loopB() {
        while (this.num != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("B");
        this.num = 3;
        this.notifyAll();
    }

    public synchronized void loopC() {
        while (this.num != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.print("C");
        this.num = 1;
        this.notifyAll();
    }
}

class MyABCLoop2 {

    private Integer num = 1;

    private Lock lock = new ReentrantLock();
    Condition conditionA = this.lock.newCondition();
    Condition conditionB = this.lock.newCondition();
    Condition conditionC = this.lock.newCondition();

    public void loopA() {
        try {
            this.lock.lock();
            if (this.num != 1) {
                this.conditionA.await();
            }
            System.out.print("A");
            this.num = 2;
            this.conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void loopB() {
        try {
            this.lock.lock();
            if (this.num != 2) {
                this.conditionB.await();
            }
            System.out.print("B");
            this.num = 3;
            this.conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void loopC() {
        try {
            this.lock.lock();
            if (this.num != 3) {
                this.conditionC.await();
            }
            System.out.print("C");
            this.num = 1;
            this.conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }
}

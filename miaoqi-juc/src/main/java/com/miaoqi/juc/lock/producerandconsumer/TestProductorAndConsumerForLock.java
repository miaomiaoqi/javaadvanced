package com.miaoqi.juc.lock.producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 生产者消费者案例：
 */
public class TestProductorAndConsumerForLock {
    public static void main(String[] args) {
        ClerkLock clerk = new ClerkLock();

        ProductorLock pro = new ProductorLock(clerk);
        ConsumerLock con = new ConsumerLock(clerk);

        new Thread(pro, "生产者 A").start();
        new Thread(con, "消费者 B").start();

        //		 new Thread(pro, "生产者 C").start();
        //		 new Thread(con, "消费者 D").start();
    }

}

class ClerkLock {
    private int product = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = this.lock.newCondition();

    // 进货
    public void get() {
        this.lock.lock();

        try {
            if (this.product >= 1) { // 为了避免虚假唤醒，应该总是使用在循环中。
                System.out.println("产品已满！");

                try {
                    this.condition.await();
                } catch (InterruptedException e) {
                }

            }
            System.out.println(Thread.currentThread().getName() + " : " + ++this.product);

            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }

    }

    // 卖货
    public void sale() {
        this.lock.lock();

        try {
            if (this.product <= 0) {
                System.out.println("缺货！");

                try {
                    this.condition.await();
                } catch (InterruptedException e) {
                }
            }

            System.out.println(Thread.currentThread().getName() + " : " + --this.product);

            this.condition.signalAll();

        } finally {
            this.lock.unlock();
        }
    }
}

// 生产者
class ProductorLock implements Runnable {

    private ClerkLock clerk;

    public ProductorLock(ClerkLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.clerk.get();
        }
    }
}

// 消费者
class ConsumerLock implements Runnable {

    private ClerkLock clerk;

    public ConsumerLock(ClerkLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.clerk.sale();
        }
    }

}
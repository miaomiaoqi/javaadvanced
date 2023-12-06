package com.miaoqi.juc.lock.producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *题目: 一个初始值为零的变量, 两个线程对其交替操作, 一个加 1 一个减 1, 来 5 轮
 *
 * @author miaoqi
 * @date 2023-12-06 20:21:32
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        }, "BB").start();

    }

}

/**
 * 资源类
 *
 * @author miaoqi
 * @date 2023-12-06 20:22:28
 */
class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = this.lock.newCondition();

    public void increment() {
        this.lock.lock();
        // 1. 判断
        try {
            while (this.number != 0) {
                // 等待, 不能生产
                try {
                    this.condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 2. 干活
            this.number++;
            System.out.println(Thread.currentThread().getName() + "\t " + this.number);
            // 3. 通知唤醒
            this.condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void decrement() {
        this.lock.lock();
        // 1. 判断
        try {
            while (this.number == 0) {
                // 等待, 不能生产
                try {
                    this.condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 2. 干活
            this.number--;
            System.out.println(Thread.currentThread().getName() + "\t " + this.number);
            // 3. 通知唤醒
            this.condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }


}

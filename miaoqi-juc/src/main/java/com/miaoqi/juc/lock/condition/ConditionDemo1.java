package com.miaoqi.juc.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 演示Condition的基本用法
 */
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = this.lock.newCondition();

    void method1() throws InterruptedException {
        this.lock.lock();
        try {
            System.out.println("条件不满足，开始await");
            this.condition.await();
            System.out.println("条件满足了，开始执行后续的任务");
        } finally {
            this.lock.unlock();
        }
    }

    void method2() {
        this.lock.lock();
        try {
            System.out.println("准备工作完成，唤醒其他的线程");
            this.condition.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        conditionDemo1.method1();
    }

}
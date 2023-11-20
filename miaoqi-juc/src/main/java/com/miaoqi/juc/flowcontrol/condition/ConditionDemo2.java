package com.miaoqi.juc.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：     演示用Condition实现生产者消费者模式
 */
public class ConditionDemo2 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(this.queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = this.lock.newCondition();
    private Condition notEmpty = this.lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Producer producer = conditionDemo2.new Producer();
        Consumer consumer = conditionDemo2.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            this.consume();
        }

        private void consume() {
            while (true) {
                ConditionDemo2.this.lock.lock();
                try {
                    while (ConditionDemo2.this.queue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        try {
                            ConditionDemo2.this.notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ConditionDemo2.this.queue.poll();
                    ConditionDemo2.this.notFull.signalAll();
                    System.out.println("从队列里取走了一个数据，队列剩余" + ConditionDemo2.this.queue.size() + "个元素");
                } finally {
                    ConditionDemo2.this.lock.unlock();
                }
            }
        }

    }

    class Producer extends Thread {

        @Override
        public void run() {
            this.produce();
        }

        private void produce() {
            while (true) {
                ConditionDemo2.this.lock.lock();
                try {
                    while (ConditionDemo2.this.queue.size() == ConditionDemo2.this.queueSize) {
                        System.out.println("队列满，等待有空余");
                        try {
                            ConditionDemo2.this.notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ConditionDemo2.this.queue.offer(1);
                    ConditionDemo2.this.notEmpty.signalAll();
                    System.out.println("向队列插入了一个元素，队列剩余空间" + (ConditionDemo2.this.queueSize - ConditionDemo2.this.queue.size()));
                } finally {
                    ConditionDemo2.this.lock.unlock();
                }
            }
        }

    }

}
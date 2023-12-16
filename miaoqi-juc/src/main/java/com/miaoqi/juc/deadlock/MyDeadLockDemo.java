package com.miaoqi.juc.deadlock;


import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (this.lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + this.lockA + "\t 尝试获得: " + this.lockB);
            // 暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this.lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + this.lockB + "\t 尝试获得: " + this.lockA);
            }
        }
    }

}

public class MyDeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        // jps 查看 java 进程 jps -l
        // jstack 查看 java 栈信息

    }

}

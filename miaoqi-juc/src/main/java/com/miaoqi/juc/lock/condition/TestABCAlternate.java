package com.miaoqi.juc.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 *	如：ABCABCABC…… 依次递归
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopA(i);
                }

            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopB(i);
                }

            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopC(i);

                    System.out.println("-----------------------------------");
                }

            }
        }, "C").start();
    }

}

class AlternateDemo {

    private int number = 1; //当前正在执行线程的标记

    private Lock lock = new ReentrantLock();
    private Condition condition1 = this.lock.newCondition();
    private Condition condition2 = this.lock.newCondition();
    private Condition condition3 = this.lock.newCondition();

    /**
     * @param totalLoop : 循环第几轮
     */
    public void loopA(int totalLoop) {
        this.lock.lock();

        try {
            //1. 判断
            if (this.number != 1) {
                this.condition1.await();
            }

            //2. 打印
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. 唤醒
            this.number = 2;
            this.condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void loopB(int totalLoop) {
        this.lock.lock();

        try {
            //1. 判断
            if (this.number != 2) {
                this.condition2.await();
            }

            //2. 打印
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. 唤醒
            this.number = 3;
            this.condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public void loopC(int totalLoop) {
        this.lock.lock();

        try {
            //1. 判断
            if (this.number != 3) {
                this.condition3.await();
            }

            //2. 打印
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. 唤醒
            this.number = 1;
            this.condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }
}

class TestABC {

    private int num = 0;

    public static void main(String[] args) {
        TestABC abc = new TestABC();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printA();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printB();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printC();
                }
            }
        }, "C").start();
    }

    public synchronized void printA() {
        while (this.num != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        this.num = 1;
        this.notifyAll();
    }

    public synchronized void printB() {
        while (this.num != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        this.num = 2;
        this.notifyAll();
    }

    public synchronized void printC() {
        while (this.num != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        this.num = 0;
        this.notifyAll();
    }
}

class TestABCLock {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    Condition conditionA = this.lock.newCondition();
    Condition conditionB = this.lock.newCondition();
    Condition conditionC = this.lock.newCondition();

    public static void main(String[] args) {
        TestABCLock abc = new TestABCLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printA();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printB();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    abc.printC();
                }
            }
        }, "C").start();
    }

    public void printA() {
        try {
            this.lock.lock();
            if (this.num != 0) {
                try {
                    this.conditionA.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.print(Thread.currentThread().getName());
            this.num++;
            this.conditionB.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public void printB() {
        try {
            this.lock.lock();
            if (this.num != 1) {
                try {
                    this.conditionB.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.print(Thread.currentThread().getName());
            this.num++;
            this.conditionC.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public void printC() {
        try {
            this.lock.lock();
            if (this.num != 2) {
                try {
                    this.conditionC.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.print(Thread.currentThread().getName());
            this.num++;
            this.conditionA.signal();
        } finally {
            this.lock.unlock();
        }
    }
}
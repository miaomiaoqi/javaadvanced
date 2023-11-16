package com.miaoqi.atguigu.juc.jmm;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }

    // 此时 number 前边是加了 volatile 的, 但是不保证原子性
    public void addPlusPlus() {
        this.num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1.验证 volatile 的可见性
 * 1.1 假如 int number = 0;, nubmer 变量之前根本没添加 volatile 关键字修饰
 * 1.2 添加了 volatile 可以解决可见性问题
 *
 * 2. 验证 volatile 不保证原子性
 * 2.1 原子性指的什么意思?
 *      不可分割, 完整性, 也即某个线程在做某个完整业务时, 中间不可以被加塞或者被分割. 需要整体完整
 *      要么同时成功, 要么同时失败
 * 2.2 如何解决:
 *      加 synchronized 关键字, 重量级
 *      使用 AtomicInteger 原子包装类
 *
 * @author miaoqi
 * @date 2023-11-15 23:11:9
 */
public class VolatileDemo1 {

    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上边 20 个线程全部计算完成以后, 在用 main 线程取得最终的结果看值是多少
        // main 线程和 gc 线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.num);
        System.out.println(Thread.currentThread().getName() + "\t atomicNumber value: " + myData.atomicInteger.get());

    }

    // volatile 可以保证可见性, 及时通知其他线程, 主物理内存的值已经修改
    public static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.num);
        }, "AAA").start();

        // 第二个线程 main 线程
        while (myData.num == 0) {
            // main 线程一直在这里循环等待, 直到 nubmer 不等于 0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}

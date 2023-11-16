package com.miaoqi.atguigu.juc.day01;


import java.util.concurrent.TimeUnit;

class MyData {

    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }

}

/**
 * 1.验证 volatile 的可见性
 * 1.1 假如 int number = 0;, nubmer 变量之前根本没添加 volatile 关键字修饰
 *
 * @author miaoqi
 * @date 2023-11-15 23:11:9
 */
public class VolatileDemo1 {

    public static void main(String[] args) {
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

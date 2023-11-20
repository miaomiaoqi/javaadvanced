package com.miaoqi.atguigu.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA 问题的解决 AtomicStampedReference
 *
 * @author miaoqi
 * @date 2023-11-20 16:27:50
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("=========以下是 ABA 问题的产生=========");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(atomicReference.compareAndSet(100, 2023) + "\t" + atomicReference.get());
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("=========以下是 ABA 问题的解决=========");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第 1 次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
                atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\t第 2 次版本号" + atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "\t第 3 次版本号" + atomicStampedReference.getStamp());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t3").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第 1 次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = atomicStampedReference.compareAndSet(100, 2023, stamp, atomicStampedReference.getStamp() + 1);
            System.out.println(
                    Thread.currentThread().getName() + "\t修改成功否: " + b + "\t 当前最新实际版本号: " + atomicStampedReference.getStamp());
            System.out.println(
                    Thread.currentThread().getName() + "\t修改成功否: " + b + "\t 当前实际最新值: " + atomicStampedReference.getReference());
        }, "t4").start();
    }

}

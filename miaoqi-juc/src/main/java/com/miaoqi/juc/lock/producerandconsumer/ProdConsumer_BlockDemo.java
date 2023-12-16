package com.miaoqi.juc.lock.producerandconsumer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 * @author miaoqi
 * @date 2023-12-06 23:43:59
 */
public class ProdConsumer_BlockDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到, 大老板 main 叫停, 活动结束");
        myResource.stop();
    }

}

class MyResource {

    private volatile boolean FLAG = true; // 默认开启, 进行生产 + 消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retVal;
        while (this.FLAG) {
            data = this.atomicInteger.incrementAndGet() + "";
            retVal = this.blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retVal) {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "大老板叫停了, 表示 FLAG = false, 生产动作结束");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (this.FLAG) {
            result = this.blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                this.FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t" + "超过 2 秒钟没有取到蛋糕, 消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "消费队列" + result + "成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }

}

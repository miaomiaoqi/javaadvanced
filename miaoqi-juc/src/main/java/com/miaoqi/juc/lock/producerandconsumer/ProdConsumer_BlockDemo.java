package com.miaoqi.juc.lock.producerandconsumer;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 * @author miaoqi
 * @date 2023-12-06 23:43:59
 */
public class ProdConsumer_BlockDemo {

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
        while (this.FLAG) {
            this.atomicInteger.incrementAndGet();
        }
    }

}

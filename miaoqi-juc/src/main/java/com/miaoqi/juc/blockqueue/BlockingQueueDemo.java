package com.miaoqi.juc.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列, 此队列按先进先出(FIFO)原则对元素进行排序
 * LinkedBlockingQueue: 是一个基于链表结构的阻塞队列, 此队列按先进先出(FIFO)排序元素, 吞吐量通常要高于 ArrayBlockingQueue
 * SynchronousQueue: 一个不存储元素的阻塞队列, 每个插入操作必须等到另一个线程调用移除操作, 否则插入操作一直处于阻塞状态
 *
 * 1. 队列
 * 2. 阻塞队列
 *      2.1 阻塞队列有没有好的一面
 *      2.2 不得不阻塞, 你如何管理
 *
 * @author miaoqi
 * @date 2023-11-25 17:56:12
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // testException();
        // testBoolean();
        // testBlock();
        testOvertime();
    }

    public static void testOvertime() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a", 2, TimeUnit.SECONDS);
        blockingQueue.offer("a", 2, TimeUnit.SECONDS);
        blockingQueue.offer("a", 2, TimeUnit.SECONDS);
        blockingQueue.offer("a", 2, TimeUnit.SECONDS);
    }

    /**
     * 测试阻塞
     *
     * @author miaoqi
     * @date 2023-12-04 20:36:26
     *
     * @return
     */
    private static void testBlock() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("=======");
        System.err.println("dazgfdag");
        // 队列已满会一直阻塞
        // blockingQueue.put("x");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        // 队列已空会一直阻塞
        blockingQueue.take();
    }

    public static void testException() {
        // 先进先出的阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 会返回 null
        System.out.println(blockingQueue.remove());
    }

    public static void testBoolean() {
        // 先进先出的阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

}

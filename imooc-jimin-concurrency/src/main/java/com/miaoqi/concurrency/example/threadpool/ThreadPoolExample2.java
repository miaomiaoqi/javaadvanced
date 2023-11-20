package com.miaoqi.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定数量的线程池
 *
 * @author miaoqi
 * @date 2019/11/22
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.submit(() ->
                    log.info("task: {}", index)
            );
        }
        executorService.shutdown();
    }

}

package com.miaoqi.juc.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单一线程的线程池
 *
 * @author miaoqi
 * @date 2019/11/22
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        // 单线程的, 所以结果是顺序的
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.submit(() ->
                    log.info("task: {}", index)
            );
        }
        executorService.shutdown();
    }

}

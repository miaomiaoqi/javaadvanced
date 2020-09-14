package concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可调度的线程池
 *
 * @author miaoqi
 * @date 2019/11/22
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        // executorService.schedule(() ->
        //                 log.info("schedule run")
        //         , 3, TimeUnit.SECONDS);
        // 延迟一秒, 每隔三秒执行任务
        executorService.scheduleAtFixedRate(() -> log.info("schedule run"), 1, 3, TimeUnit.SECONDS);

        // executorService.shutdown();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        }, new Date(), 5 * 1000);
    }

}

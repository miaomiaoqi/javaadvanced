package com.miaoqi.atguigu.java9.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // 保存订阅关系, 需要用它来给发布者响应
        this.subscription = subscription;

        // 请求一个数据
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        // 接收到一个数据处理
        System.out.println("处理器接收到数据: " + item);

        // 过滤掉小于0的数据, 然后发布出去
        if (item > 0) {
            this.submit("转换后的数据: " + item);
        }

        // 处理完调用request在请求一个数据
        this.subscription.request(1);

        // 或者已经达到了目标, 调用cancel告诉发布者不再接收数据了
        // this.subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        System.out.println("处理器处理完了");
    }
}

public class FlowDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 定义发布者, 发布的数据类型是Integer
        // 直接使用jdk自带的SubmissionPublisher, 它实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2. 定义处理器, 对数据进行过滤, 并转换为String类型
        MyProcessor processor = new MyProcessor();

        // 3. 发布者和处理器建立订阅关系
        publisher.subscribe(processor);

        // 4. 定义最终订阅者, 消费String类型数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 保存订阅关系, 需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                // 接收到一个数据, 处理
                System.out.println("接受到数据: " + item);

                // 处理完调用request再请求一个数据
                this.subscription.request(1);

                // 或者已经达到了目标. 调用cancel告诉发布者不再接收数据了
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现了异常(例如处理数据的时候产生了异常)
                throwable.printStackTrace();
                // 我们可以告诉发布者后边不再接收数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部处理完成了(发布者关闭了)
                System.out.println("处理完了");
            }
        };

        // 5. 处理器和最终订阅者建立订阅关系
        processor.subscribe(subscriber);

        // 6. 生产数据并发布
        publisher.submit(-111);
        publisher.submit(111);

        // 7. 结束后, 关闭发布者
        publisher.close();

        // 主线程延迟停止
        Thread.currentThread().join(1000);
    }
}

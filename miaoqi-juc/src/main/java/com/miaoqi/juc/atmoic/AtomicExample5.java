package com.miaoqi.juc.atmoic;
import com.miaoqi.juc.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicReferenceFieldUpdater用来修改某一个类的某一个值, 必须是非static, volatile修饰的变量
 *
 * @author miaoqi
 * @date 2018/11/4
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(
            AtomicExample5.class
            , "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update access 1, {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update access 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }

}

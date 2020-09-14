package com.miaoqi.java.reflect.test;

/**
 * 声卡
 *
 * @author miaoqi
 * @date 2019-07-17
 */
public class SoundCard implements PCI {
    @Override
    public void open() {
        System.out.println("sound open");
    }

    @Override
    public void close() {
        System.out.println("sound close");
    }

}

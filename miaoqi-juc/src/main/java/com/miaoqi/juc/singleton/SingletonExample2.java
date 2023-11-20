package com.miaoqi.juc.singleton;

/**
 * 安全发布对象
 * 饿汉模式, 单实例在类装载的时候就创建对象
 *
 * @author miaoqi
 * @date 2018/11/8
 */
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {
    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法获取单例对象, 如果没有调用这个单例对象, 会造成资源浪费
    public static SingletonExample2 getInstance() {
        return instance;
    }


}

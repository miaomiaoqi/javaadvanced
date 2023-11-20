package com.miaoqi.concurrency.example.singleton;

/**
 * 安全发布对象
 * 饿汉模式, 单实例在类装载的时候就创建对象
 *
 * @author miaoqi
 * @date 2018/11/8
 */
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {
    }

    // 单例对象, 静态代码块要在静态成员变量后初始化, 因为是按顺序执行的
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法获取单例对象, 如果没有调用这个单例对象, 会造成资源浪费
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args){
        System.out.println(getInstance());
        System.out.println(getInstance());
    }

}

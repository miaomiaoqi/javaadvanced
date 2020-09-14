package concurrency.example.singleton;

import com.miaoqi.concurrency.annotations.NotThreadSafe;

/**
 * 安全发布对象
 * 懒汉模式 -> 双重同步锁单例模式, 单实例在第一次访问的时候才初始化对象
 *
 * @author miaoqi
 * @date 2018/11/8
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {
    }

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample4 getInstance() {
        // 这里不安全, 但是通过双重检测机制和锁的配合改善了性能问题, 但还是存在安全问题, 原因如下
        // 在执行new SingletomExample4()时, CPU指令如下
        // 1. memory = allocate() 分配内存空间
        // 2. ctorInstance() 初始化对象
        // 3. instance = memory 设置instance指向刚分配的内存空间

        // JVM和CPU优化, 发生了指令重排
        // 1. memory = allocate() 分配内存空间
        // 3. instance = memory 设置instance指向刚分配的内存空间
        // 2. ctorInstance() 初始化对象

        // A线程刚执行到第3步, 实际上并没有初始化对象, 这时B线程判断非空, 如果被使用就会异常
        if (instance == null) { // B
            synchronized(SingletonExample4.class){
                if (instance == null) {
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        return instance;
    }


}

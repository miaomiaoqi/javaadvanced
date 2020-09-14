package concurrency.example.singleton;

import com.miaoqi.concurrency.annotations.NotThreadSafe;

/**
 * 安全发布对象
 * 懒汉模式, 单实例在第一次访问的时候才初始化对象
 *
 * @author miaoqi
 * @date 2018/11/8
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {
    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample1 getInstance() {
        // 这里不安全
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }


}

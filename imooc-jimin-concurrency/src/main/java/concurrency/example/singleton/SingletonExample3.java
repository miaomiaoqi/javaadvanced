package concurrency.example.singleton;

/**
 * 安全发布对象
 * 懒汉模式, 单实例在第一次访问的时候才初始化对象
 *
 * @author miaoqi
 * @date 2018/11/8
 */
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {
    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法获取单例对象
    public static synchronized SingletonExample3 getInstance() {
        // 这里不安全, 但是通过加锁可以保证懒汉的安全, 可性能太低了
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }


}

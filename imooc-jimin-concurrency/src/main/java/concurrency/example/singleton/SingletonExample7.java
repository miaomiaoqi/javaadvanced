package concurrency.example.singleton;

/**
 * 安全发布对象
 * 枚举生成单例对象, 性能与安全都是最好的
 *
 * @author miaoqi
 * @date 2018/11/8
 */
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保 证枚举的构造函数绝对只调用一次
        private Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }

}

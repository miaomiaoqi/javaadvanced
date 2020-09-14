package com.miaoqi.itheima.jvmdemo.t1.stringtable;

/**
 * 演示字符串相关面试题
 */
public class Demo1_21 {

    public static void main(String[] args) {
        String s1 = "a"; // 用到时会放入到串池中
        String s2 = "b"; // 串池
        String s3 = "ab"; // 串池
        String s4 = s1 + s2; // new StringBuilder().toString() 放入到堆中 new String("ab")
        String s5 = "a" + "b"; // javac 在编译期间的优化, s5 的结果已经在编译时确定为 ab, 所以 s5 会从串池中查找 "ab", 因为 s3 已经将 "ab" 放入到串池中, 所以 s5 直接引用串池中的 "ab"
        String s6 = s4.intern(); // s4.intern() 方法会尝试将 s4 引用的对象放入常量池中, 但此时串池中已经有了 "ab", 所以 s4 依旧指向堆中对象, 会将 s6 指向串池中的 "ab"

        // 问
        System.out.println(s3 == s4); // s3 在串池中, s4 在堆中, 所以 false
        System.out.println(s3 == s5); // s3 和 s5 均引用串池中的 "ab" 所以 true
        System.out.println(s3 == s6); // s4.intern() 会尝试将 "ab" 放入常量池中并返回, s3 直接引用常量池, 所以 true

        String x2 = new String("c") + new String("d"); // x2 指向堆中 new String("cd")
        String x1 = "cd"; // x1 指向串池中 "cd"
        x2.intern(); // 尝试将 x2 的对象放入串池中, 但串池中已经有了 "cd", 所以 x2 依旧指向堆中 new String("cd")

        // 问，如果调换了【最后两行代码】的位置呢，如果是jdk1.6呢
        System.out.println(x1 == x2); // false
    }
}

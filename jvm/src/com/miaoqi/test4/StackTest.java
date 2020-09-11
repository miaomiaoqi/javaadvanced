package com.miaoqi.test4;

// 每个方法执行都会创建一个栈桢, 如果创建过多, 就会StackOverflowError
public class StackTest {

    public static void tes() {
        System.out.println("方法执行...");
        tes();
    }

    public static void main(String[] args) {
        tes();
    }

}

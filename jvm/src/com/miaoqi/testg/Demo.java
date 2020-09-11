package com.miaoqi.testg;

public class Demo {

    static int A = 1;

    static {
        A = 0;
        System.out.println(A);
    }
    //    static int A = 1;

    public static void main(String[] args) {
        new Demo();
    }

}

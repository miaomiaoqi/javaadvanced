package com.miaoqi.test5;

public class Test {

    public static void main(String[] args) {

        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 == s2);

        String s3 = new String("abc");

        System.out.println(s1 == s3);

        System.out.println(s1 == s3.intern()); // intern会将堆内存放到运行时常量池

    }

}

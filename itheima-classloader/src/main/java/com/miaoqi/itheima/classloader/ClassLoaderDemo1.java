package com.miaoqi.itheima.classloader;

import sun.net.spi.nameservice.dns.DNSNameService;

public class ClassLoaderDemo1 {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderDemo1.class.getClassLoader();
        while (cl!=null){
            System.out.println(cl);
            cl = cl.getParent();
        }
        System.out.println("-------------------");
        ClassLoader cl1 = Object.class.getClassLoader();
        System.out.println("cl1:"+cl1);
        System.out.println("-------------------");
        ClassLoader cl2 = DNSNameService.class.getClassLoader();
        System.out.println("cl2:"+cl2);
        System.out.println("-------------------");
        ClassLoader cl3 = MyObject.class.getClassLoader();
        System.out.println(cl3);
    }
}

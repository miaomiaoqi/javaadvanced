package com.miaoqi.javase.classloader;

// 同一个类被不同类加载器多次加载（热部署）
public class ClassLoaderDemo3 {

    public static void main(String[] args) throws Exception {
        MyFileClassLoader cl1 = new MyFileClassLoader("d:/");//cl1的父类加载是AppClassLoader
        MyFileClassLoader cl2 = new MyFileClassLoader("d:/", cl1);//cl1和cl2是父子关系
        //        Class clazz1 = cl1.loadClass("com.itheima.Demo");
        //        Class clazz2 = cl2.loadClass("com.itheima.Demo");
        //        System.out.println("clazz1:"+clazz1.hashCode());
        //        System.out.println("clazz2:"+clazz2.hashCode());
        Class clazz1 = cl1.findClass("com.itheima.Demo");
        Class clazz2 = cl2.findClass("com.itheima.Demo");
        System.out.println("clazz1:" + clazz1.hashCode());
        System.out.println("clazz2:" + clazz2.hashCode());
    }

}

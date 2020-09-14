package com.miaoqi.itheima.classloader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderDemo2 {
    public static void main(String[] args) throws Exception {
        test2();
    }
    //加载网络上的类
    public static void test2() throws Exception{
        URL url = new URL("http://localhost:8080/examples/");
        URLClassLoader cl = new URLClassLoader(new URL[]{url});
        Class clazz = cl.loadClass("com.itheima.Demo");
        clazz.newInstance();
    }
    //加载d盘上指定的类
    public static void test1() throws Exception{
        File path = new File("d:/");
        URI uri = path.toURI();
        URL url = uri.toURL();
        URLClassLoader cl = new URLClassLoader(new URL[]{url});
        Class clazz = cl.loadClass("com.itheima.Demo");
        clazz.newInstance();
    }
}

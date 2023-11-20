package com.miaoqi.javase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyURLClassLoader extends ClassLoader {

    private String url;//类所在的网络地址

    public MyURLClassLoader(String url) {//默认的父类加载器：AppClassLoader
        this.url = url;
    }

    public MyURLClassLoader(String url, ClassLoader parent) {
        super(parent);
        this.url = url;
    }

    //http://localhost:8080/examples         com.itheima.Demo

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //组装URL地址
            String path = url + "/" + name.replace(".", "/") + ".class";
            URL url = new URL(path);
            //构建输入流
            InputStream in = url.openStream();
            //构建字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //读取内容
            int len = -1;
            byte[] buf = new byte[2048];
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] data = baos.toByteArray();//class的二进制数据
            in.close();
            baos.close();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        MyURLClassLoader cl = new MyURLClassLoader("http://localhost:8080/examples");
        Class<?> aClass = cl.loadClass("com.itheima.Demo");
        aClass.newInstance();
    }

}

package com.miaoqi.javase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//1、继承ClassLoader
//2、覆盖findClass方法，并使用defineClass返回Class对象
public class MyFileClassLoader extends ClassLoader {

    private String directory;//字节码所存放的目录

    public MyFileClassLoader(String directory) {//父类加载器：AppClassLoader系统类加载器
        this.directory = directory;
    }

    public MyFileClassLoader(String directory, ClassLoader parent) {
        super(parent);
        this.directory = directory;
    }

    // com.itheima.Demo      要读取的文件：com/itheima/Demo.class
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //包名转换为目录
            String file = directory + File.separator + name.replace(".", File.separator) + ".class";
            //构建输入流
            InputStream in = new FileInputStream(file);
            //构建输出流:ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //读取文件
            int len = -1;//读取到的数据的长度
            byte[] buf = new byte[2048];//缓存
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] data = baos.toByteArray();
            in.close();
            baos.close();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        MyFileClassLoader cl = new MyFileClassLoader("d:/");
        Class<?> aClass = cl.loadClass("com.itheima.Demo");
        aClass.newInstance();
    }

}

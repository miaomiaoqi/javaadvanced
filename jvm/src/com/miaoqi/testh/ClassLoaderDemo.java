package com.miaoqi.testh;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        ClassLoader mycl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    return defineClass(name, buffer, 0, buffer.length);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        };
        Object c = mycl.loadClass("com.miaoqi.testh.ClassLoaderDemo").newInstance();
        System.out.println(c.getClass());
        System.out.println(c instanceof ClassLoaderDemo);
    }

}

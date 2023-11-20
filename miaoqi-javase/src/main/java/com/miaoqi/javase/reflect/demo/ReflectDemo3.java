package com.miaoqi.javase.reflect.demo;

import java.lang.reflect.Field;

public class ReflectDemo3 {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        getFieldDemo();
    }

    /**
     * 获取字节码文件中的字段。
     *
     * @author miaoqi
     * @date 2019-07-17
     * @param
     * @return
     */
    public static void getFieldDemo() throws Exception {

        Class clazz = Class.forName("cn.itcast.bean.Person");

        // clazz.getField("age");//只能获取公有的，
        Field field = null;

        // 只获取本类，但包含私有。
        field = clazz.getDeclaredField("age");

        //对私有字段的访问取消权限检查。暴力访问。
        field.setAccessible(true);

        Object obj = clazz.newInstance();

        field.set(obj, 89);

        Object o = field.get(obj);

        System.out.println(o);

        //		cn.itcast.bean.Person p = new cn.itcast.bean.Person();
        //		p.age = 30;
    }

}

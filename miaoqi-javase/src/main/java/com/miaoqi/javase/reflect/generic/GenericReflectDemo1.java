package com.miaoqi.javase.reflect.generic;


import com.miaoqi.javase.reflect.bean.PersonDao;

/**
 * 子类继承父类, 并且实现一个接口
 * 在父类构造函数中, 获取子类信息
 *
 * @author miaoqi
 * @date 2019-07-18
 */
public class GenericReflectDemo1 {

    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();
    }

}

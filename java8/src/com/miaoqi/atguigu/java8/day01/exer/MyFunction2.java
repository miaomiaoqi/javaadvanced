package com.miaoqi.atguigu.java8.day01.exer;

/**
 * @author miaoqi
 * @date 2017-09-26 下午1:34
 **/
@FunctionalInterface
public interface MyFunction2<T, R> {

    R getValue(T t1, T t2);

}

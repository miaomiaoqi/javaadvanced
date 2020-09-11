package com.miaoqi.atguigu.java8.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    //Predicate<T> 断言型接口：
    @Test
    public void test4() {
        List<String> list = Arrays.asList("hahaha", "a", "b", "dg", "sdgdsg", "fd", "adgmkp");
        List<String> newList = new ArrayList<>();
        newList = filterStr(list, s -> s.length() > 3);
        for (String s : newList) {
            System.out.println(s);
        }
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

    //Function<T, R> 函数型接口：
    @Test
    public void test3() {
        String newStr = strHandler("hahaha", str -> str.toUpperCase());
        System.out.println(newStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //Supplier<T> 供给型接口 :
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> new Random().nextInt(10));
        numList.forEach(System.out::println);
    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            nums.add(supplier.get());
        }
        return nums;
    }

    //Consumer<T> 消费型接口 :
    @Test
    public void test1() {
        this.happy(1000, m -> System.out.println("大保健消费" + m));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }
}

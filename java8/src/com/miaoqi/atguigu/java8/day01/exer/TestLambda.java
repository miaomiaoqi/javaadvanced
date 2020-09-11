package com.miaoqi.atguigu.java8.day01.exer;

import com.miaoqi.java8.day01.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author miaoqi
 * @date 2017-09-26 下午12:50
 **/
public class TestLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 66, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            int temp = Integer.compare(e1.getAge(), e2.getAge());
            return temp == 0 ? e1.getName().compareTo(e2.getName()) : temp;
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2() {
        String str = strHandler("\t\t\tI love Java", s -> s.trim());
        System.out.println(str);
    }

    // 需求: 用于处理字符串
    public String strHandler(String string, MyFunction mf) {
        return mf.getValues(string);
    }

    @Test
    public void test3() {
        op(100L, 200L, (l1, l2) -> l1 + l2);
        op(100L, 200L, (l1, l2) -> l1 * l2);
    }

    // 需求: 对于两个Long型数据进行处理
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

}

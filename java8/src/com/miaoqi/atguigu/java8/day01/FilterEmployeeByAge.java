package com.miaoqi.atguigu.java8.day01;

/**
 * @author miaoqi
 * @date 2017-09-23 下午7:21
 **/
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}

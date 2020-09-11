package com.miaoqi.atguigu.java8.day01;

/**
 * @author miaoqi
 * @date 2017-09-23 下午7:29
 **/
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}

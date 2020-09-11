package com.miaoqi.atguigu.java8.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author miaoqi
 * @date 2017/9/18
 * @description 测试Lambda表达式
 */
public class TestLambda {

    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
    }

    // Lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 66, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test3() {
        List<Employee> employees = filterEmployees2(this.employees);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // 需求1: 获取当前公司中员工年龄大于35的员工信息
    public List<Employee> filterEmployees1(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() > 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 需求2: 获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 优化方式一:
    @Test
    public void test4() {
        List<Employee> emps = filterEmployees(this.employees, new FilterEmployeeByAge());
        for (Employee emp : emps) {
            System.out.println(emp);
        }
        System.out.println("---------------------");
        emps = filterEmployees(this.employees, new FilterEmployeeBySalary());
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.test(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }

    // 优化方式二: 匿名内部类
    @Test
    public void test5() {
        List<Employee> emps = filterEmployees(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });
        //        for (Employee emp : emps) {
        //            System.out.println(emp);
        //        }
    }

    // 优化方式三: Lambda表达式
    @Test
    public void test6() {
        List<Employee> emps = filterEmployees(this.employees, e -> e.getSalary() <= 5000);
        emps.forEach(System.out::println);
    }

    // 优化方式四: Stream API
    @Test
    public void test7() {
        employees.stream().filter(e -> e.getSalary() >= 5000).limit(2).forEach(System.out::println);
        System.out.println("-----------------");
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }
}

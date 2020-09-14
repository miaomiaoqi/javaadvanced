package com.miaoqi.java.reflect.bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取父类泛型
 *
 * @author miaoqi
 * @date 2019-07-18
 */
public class BaseDao<T> {

    public Class<T> clazz;

    public BaseDao() {
        // this 指向子类对象, new PersonDao()时会首先调用父类构造函数
        // 获取泛型父类定义
        // BaseDao<Perdon>
        Type type = this.getClass().getGenericSuperclass();
        System.out.println("获取父类泛型: " + type.getTypeName());
        // 获取子类实现的泛型接口
        Type[] genericInterfaces = this.getClass().getGenericInterfaces();
        System.out.println("获取泛型接口: " + genericInterfaces[0].getTypeName());

        // 带泛型的 Type 就是 ParameterizedType 的实例对象, 可以强转为 ParameterizedType
        ParameterizedType pt = (ParameterizedType) type;
        // 获取泛型参数最外层的类型
        System.out.println("根据父类泛型获取最外层类型: " + pt.getRawType().getTypeName());
        // 获取泛型参数的泛型类型
        System.out.println("获取泛型参数的泛型类型: " + ((Class)pt.getActualTypeArguments()[0]).getTypeName());

        // 不带泛型的 Type 就是 Class 的实例对象, 可以强转为 Class
    }

}

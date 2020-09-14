package com.miaoqi.java.reflect.generic;

import com.miaoqi.java.reflect.bean.PersonDao;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取方法返回泛型
 *
 * @author miaoqi
 * @date 2019-07-18
 */
public class GenericReflectDemo2 {

    public static void main(String[] args) throws Exception {
        Class<PersonDao> clazz = PersonDao.class;
        // 获取非泛型返回类型
        Method method = clazz.getDeclaredMethod("person", null);
        System.out.println("method: " + method.getName());
        Class<?> returnType = method.getReturnType();
        System.out.println("returnType: " + returnType.getName());
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("genericReturnType: " + genericReturnType.getTypeName());
        System.out.println(genericReturnType instanceof ParameterizedType);

        System.out.println("-----------------------------");

        method = clazz.getDeclaredMethod("genericPerson", null);
        System.out.println("method: " + method.getName());
        // returnType 只会获取最外层类型
        returnType = method.getReturnType();
        System.out.println("returnType: " + returnType.getName());
        // genericReturnType 会连同泛型一起返回
        genericReturnType = method.getGenericReturnType();
        System.out.println("genericReturnType: " + genericReturnType.getTypeName());
        System.out.println(genericReturnType instanceof ParameterizedType);
        // 可以强转为 ParameterizedType 获取更多方法
        ParameterizedType pt = (ParameterizedType) genericReturnType;
        // 获取外层类型, 等同于 returnType
        pt.getRawType();
        // 获取泛型类型
        pt.getActualTypeArguments();
    }

}

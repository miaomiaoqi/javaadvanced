package com.miaoqi.juc.immutable;


import com.google.common.collect.Maps;
import com.miaoqi.juc.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 不可变类
 *
 * @author miaoqi
 * @date 2019/11/22
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args){
        // a = 2;
        // b = "3";
        // map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    public void test(final int a){
        // a = 1;
    }

}

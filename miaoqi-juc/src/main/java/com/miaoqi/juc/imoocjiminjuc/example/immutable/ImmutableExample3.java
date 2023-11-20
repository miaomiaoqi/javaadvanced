package com.miaoqi.juc.imoocjiminjuc.example.immutable;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.miaoqi.juc.imoocjiminjuc.example.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Guava的不可变类
 *
 * @author miaoqi
 * @date 2018/11/10
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 =
            ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();

    public static void main(String[] args){
        // list.add(4);
        // set.add(5);
        map.put(4, 5);
    }

}

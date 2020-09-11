package com.miaoqi.test1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miaoqi
 * @date 2018年5月26日
 * @description 模拟内存溢出
 * -XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m: 记录内存快照
 *
 */
public class Main {

    public static void main(String[] args) {
        List<Demo> demoList = new ArrayList<>();
        while (true) {
            demoList.add(new Demo());
        }
    }

}

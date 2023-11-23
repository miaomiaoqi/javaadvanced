package com.miaoqi.juc.collections;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全问题
 *
 * @author miaoqi
 * @date 2023-11-21 0:31:52
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        // List<String> list = new ArrayList<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }
        // java.util.ConcurrentModificationException 并发修改异常

        // 1. 故障现象
        // java.util.ConcurrentModificationException

        // 2. 导致原因

        // 3. 解决方案
        // 3.1 Vector 效率低
        // 3.2 Collections.synchronizedList(new ArrayList<>());
        // 3.3 CopyOnWriteArraylist

        // 4. 优化建议F

    }

}

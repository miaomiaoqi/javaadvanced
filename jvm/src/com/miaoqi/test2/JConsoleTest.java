package com.miaoqi.test2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * jdk目录/bin/jConsole工具可以监控java内存情况
 *
 * @author miaoqi
 * @date 2018年5月26日
 * @description
 */
public class JConsoleTest {

    public byte[] buffer = new byte[128 * 1024];

    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("start...");
        fill(1000);
    }

    private static void fill(int n) {
        List<JConsoleTest> jlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jlist.add(new JConsoleTest());
        }
    }
}

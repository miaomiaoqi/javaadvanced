package com.miaoqi.juc.imoocjiminjuc.example.synccontainer;

import com.miaoqi.juc.imoocjiminjuc.example.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };

            t1.start();
            t2.start();
        }
    }

}

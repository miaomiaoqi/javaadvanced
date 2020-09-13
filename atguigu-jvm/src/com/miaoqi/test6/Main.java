package com.miaoqi.test6;

//-verbose:gc -XX:+PrintGCDetails
public class Main {

    private Object instance;

    private byte[] m = new byte[1024 * 1024 * 20];

    public static void main(String[] args) {
        Main m1 = new Main();
        Main m2 = new Main();
        m1.instance = m2;
        m2.instance = m1;
        m1 = null;
        m2 = null;
        System.gc();
    }
}

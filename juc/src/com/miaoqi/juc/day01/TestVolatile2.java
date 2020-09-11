package com.miaoqi.juc.day01;

/**
 * @author miaoqi
 * @date 2018-01-20 上午11:17
 **/

public class TestVolatile2 {

    public static void main(String[] args) throws InterruptedException {
        Resource r = new Resource();
        Thread t1 = new Thread(new Task1(r));
        // Thread t2 = new Thread(new Task2(r));
        t1.start();
        // t2.start();
        //        Thread.sleep(10);
        while (true) {
            if (!r.flag) {
                System.out.println("------------------");
                break;
            }
        }
    }

}

class Resource {
    public Boolean flag = true;
}

class Task1 implements Runnable {

    private Resource r;

    public Task1(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (r.flag) {
            System.out.println("task1.....");
            r.flag = false;
            System.out.println(r.flag);
        }
    }
}

class Task2 implements Runnable {

    private Resource r;

    public Task2(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            if (!r.flag) {
                System.out.println("task2.....");
                break;
            }
        }
    }
}

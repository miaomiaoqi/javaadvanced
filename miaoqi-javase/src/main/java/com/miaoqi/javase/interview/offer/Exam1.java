package com.miaoqi.javase.interview.offer;

/**
 * 赋值=, 最后计算
 * =右边的从左到右加载值依次压入操作数栈
 * 实际先算哪个, 看运算符优先级
 * 自增、自减操作都是直接修改变量的值, 不经过操作数栈
 * 最后的赋值之前, 临时结果也是存储在操作数栈中
 *
 * @author miaoqi
 * @date 2023-05-12 16:27:6
 */
public class Exam1 {

    public static void main(String[] args) {
        int i = 1;
        // 1. 把 i 的值压入操作数栈(此时 i 的值已经被单独提取出来)
        // 2. i 变量自增 1
        // 3. 把操作数栈中的值赋值给 i
        // 可以理解为 int t = i; i = i + 1; i = t;
        i = i++;
        // 1. 把 i 的值压入操作数栈
        // 2. i 变量自增 1
        // 3. 把操作数栈中的值赋值给j
        // 可以理解为 int t = i; i = i + 1; j = t;
        int j = i++;
        // 1. 把 i 的值压入操作数栈
        // 2. i 变量自增 1
        // 3. 把 i 的值压入操作数栈
        // 4. 把 i 的值压入操作数栈
        // 5. i 变量自增 1
        // 6. 把操作数栈中前两个弹出求乘积结果再压入栈
        // 7. 把操作数栈中的值弹出求和再赋值给k
        int k = i + ++i * i++;
        System.out.println("i=" + i); // 4
        System.out.println("j=" + j); // 1
        System.out.println("k=" + k); // 11
    }

}

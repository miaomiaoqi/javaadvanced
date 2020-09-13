package com.miaoqi.test9;

// 逃逸分析
public class StackAllocation {

    public StackAllocation obj;

    // 方法返回StackAllocation对象, 发生逃逸
    public StackAllocation getInstance() {
        return obj == null ? new StackAllocation() : obj;
    }

    // 未成员属性赋值, 发生逃逸
    public void setObj() {
        this.obj = new StackAllocation();
    }

    // 对象的作用域仅在当前方法中有效, 没有发生逃逸
    public void useStackAllocation() {
        // 如果都没有发生逃逸, 就会放到栈上, 随着方法结束就释放, 性能会提高
        StackAllocation s = new StackAllocation();
    }

    // 引用成员变量的值, 发生逃逸
    public void useStackAllocation2() {
        StackAllocation s = getInstance();
    }

}

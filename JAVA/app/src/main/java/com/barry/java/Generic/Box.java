package com.barry.java.Generic;

public class Box<T> {
    private T t;
    public void set(T t) {
        this.t = t;
    }
    public T get() {
        return this.t;
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(10);

        Box<String> stringBox = new Box<>();
        stringBox.set("啦啦啦");

        System.out.printf("整型值为 :%d\n\n", integerBox.get());
        System.out.printf("字符串为 :%s\n", stringBox.get());

    }
}

package com.xingle.arithmetic.queue;

/**
 * Created by xingle on 2018/5/4.
 */

public class Stack {
    private long[] arr;
    private int top;

    public Stack() {
        arr = new long[10];
        top = -1;
    }

    public Stack(int maxSize) {
        arr = new long[maxSize];
        top = -1;
    }

    public void push(long value) {
        arr[++top] = value;
    }

    public long pop() {
        if (top == -1) {
            return -1;
        } else return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }
}

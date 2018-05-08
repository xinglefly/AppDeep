package com.xingle.arithmetic.linklist;

/**
 * Created by xingle on 2018/5/8.
 */

public class Node {

    //数据域
    public long data;
    //指针域
    public Node next;

    public Node(long value) {
        this.data = value;
    }

    public void display() {
        System.out.print(data + " ");
    }
}

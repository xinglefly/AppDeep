package com.xingle.arithmetic.queue;

/**
 * Created by xingle on 2018/5/4.
 */

public class MyQueue {

    long[] arr;
    int front;
    int end;
    int elements;

    public MyQueue() {
        arr = new long[10];
        elements = 0;
        front = 0;
        end = -1;
    }

    public MyQueue(int maxSize) {
        arr = new long[maxSize];
        elements = 0;
        front = 0;
        end = -1;
    }

    public void add(long value) {
        /*if (elements == 0) {
            front = 0;
            end = -1;
            arr[++end] = value;
            elements++;
        } else {*/
        if (end == arr.length - 1) {
            end = -1;
        }
        arr[++end] = value;
        elements++;

    }

    public long remove() {
        /*if (elements == 0) {
            try {
                throw new Exception("arr is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        } else {*/

        long value = arr[front++];
        if (front == arr.length) {
            front = 0;
        }
        elements--;
        return value;

    }

    public boolean isEmpty() {
        return elements == 0;
    }

    public boolean isFull() {
        return elements == arr.length;
    }

    public long peek(){
        return arr[front];
    }
}

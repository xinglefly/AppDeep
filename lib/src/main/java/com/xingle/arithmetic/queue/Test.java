package com.xingle.arithmetic.queue;

/**
 * Created by xingle on 2018/5/4.
 */

public class Test {

    public static void main(String[] args) {
//        testStatck();
        testQueue();
    }

    public static void testQueue() {
        MyQueue queue = new MyQueue(4);
        queue.add(10);
        queue.add(90);
        queue.add(22);
        queue.add(1);

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.peek());


        while (!queue.isEmpty()) {
            System.out.print(queue.remove()+",");
        }

        System.out.printf("%d,%d,%d,%d,",queue.arr.length,queue.elements,queue.front,queue.end);
        System.out.println("循环队列");
        queue.add(8);
        queue.add(18);
        queue.add(42);
        queue.add(36);

        System.out.println(queue.peek());

        while (!queue.isEmpty()) {
            System.out.print(queue.remove()+",");
        }

        System.out.printf("%d,%d,%d,%d,",queue.arr.length,queue.elements,queue.front,queue.end);


    }

    private static void testStatck() {
        Stack stack = new Stack(4);
        stack.push(34);
        stack.push(13);
        stack.push(20);
        stack.push(1);

        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + ",");
        }
        stack.pop();

        stack.push(100);
    }
}

package com.xingle.arithmetic.array;

/**
 * Created by xingle on 2018/4/26.
 */

public class Test {
    public static void main(String[] args) {
        MyArray array = new MyArray();
        array.add(10);
        array.add(8);
        array.add(34);
        array.add(29);
        array.add(17);
        array.add(2);

        array.display();

        System.out.println("[" + array.search(5) + "]");
        System.out.println("[" + array.get(2) + "]");

        array.delete(2);

        array.display();
//        array.sort();
//        array.insert(15);
    }
}

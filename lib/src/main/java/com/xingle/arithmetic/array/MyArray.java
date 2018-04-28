package com.xingle.arithmetic.array;

/**
 * Created by xingle on 2018/4/26.
 */

public class MyArray {
    private long[] arr;
    private int elements;

    public MyArray() {
        arr = new long[50];
    }

    public MyArray(int maxSize) {
        arr = new long[maxSize];
    }

    public void add(long value) {
        arr[elements] = value;
        elements++;
    }

    public void display() {
        System.out.print("[");
        for (int i = 0; i < elements; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println("]");
    }

    public long get(int index) {
        for (int i = 0; i < elements; i++) {
            if (i == index) {
                return arr[i];
            }
        }
        return -1;
    }

    public int search(long value) {
        int i;
        for (i = 0; i < elements; i++) {
            if (arr[i] == value)
                break;
        }

        if (arr[i] == value) return i;
        else return -1;
    }

    public void delete(int index) {
        if (index >= elements || index == 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for (int i = index; i < elements; i++) {
                arr[i] = arr[i + 1];
            }
            elements--;
        }
    }

    public void sort() {
        int i;
        for (i = 0; i < elements; i++) {
            for (int j = 0; j < i; j++) {
                long temp;
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        printList();
    }


    public void insert(long value) {
        sort();
        int i;
        for (i = 0; i < elements; i++) {
            if (value < arr[i])
                break;
        }

        elements++;
        for (int j = elements; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = value;

        printList();
    }


    private void printList() {
        int i;
        System.out.print("[");
        for (i = 0; i < elements; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println("]");
    }


}

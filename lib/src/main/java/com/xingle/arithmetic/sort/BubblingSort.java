package com.xingle.arithmetic.sort;

/**
 * Created by xingle on 2018/6/13.
 */

public class BubblingSort {

    public static void main(String[] args) {
        long arr[] = {23, 12, 2, 1};
        long temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }

        for (long p : arr) {
            System.out.println(p);
        }

    }

}

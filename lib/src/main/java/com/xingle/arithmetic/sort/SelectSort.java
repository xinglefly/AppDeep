package com.xingle.arithmetic.sort;

/**
 * Created by xingle on 2018/6/13.
 */

public class SelectSort {

    public static void main(String[] args) {
        long arr[] = {23, 12, 2, 1};
        long temp = 0;
        int k = 0;
        for (int i = 0; i < arr.length-1;i++){
            k = i;
            for (int j = i;j<arr.length;j++){
                if (arr[j] < arr[k]){
                    k = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }

        for (long p : arr) {
            System.out.println(p);
        }
    }
}

package com.ycjung.learn.grammar.coding_tip;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraysTip {
    public static void main(String[] args) {
        Integer[] arr1 = {10, 8, 11, 2, 3, 0};
        String[] arr2 = {"a", "b", "c", "d", "e", "f", "g", "h"};

        // 1. 오름차순 {0, 2, 3, 8, 10, 11}
        Arrays.sort(arr1);
        System.out.println("오름 차순 : " + Arrays.toString(arr1));

        // 2. 내림차순 {11, 10, 8, 3, 2, 0}
        Arrays.sort(arr1, Collections.reverseOrder());
        System.out.println("내림 차순 : " + Arrays.toString(arr1));

        // 3. 일부만 정렬 {2, 8, 11, 10, 3, 0} (0~4만 정렬)
        Arrays.sort(arr1, 0, 4);
        System.out.println("0번 ~ 4번만 정렬 : " + Arrays.toString(arr1));

        // 4. 오름차순 정렬하면 binary search로 특정 값을 찾을 수 있다.

        System.out.println("binary search 로 특정 값(2) 값 찾기 : " + Arrays.binarySearch(arr1, 2));

        // 5. 배열을 어레이리스트로 변환할 떼!
        List list = Arrays.asList(arr1);
        System.out.println(list.toString());

        // 6. 배열의 특정 범위 자르기
        Integer tmp[] = Arrays.copyOfRange(arr1, 0, 3);
        System.out.println("배열의 특정 범위 자르기(0~3) " + Arrays.toString(tmp));


        String arr = "abcdefghijklmnopqrstuvwxyz";

        for(int i=0; i<arr.length(); i++){
            System.out.print(arr.charAt(i) + " ");
        }

        //Integer(int value) -> new Integer 연산은 자바 특정 버전 이후에 사용 불가!
        Integer int1 = 111;

        //Integer(String s)
        Integer int2 = 123;

        Integer[] iarr = new Integer[5];
        Integer a =  111;
        System.out.println(Arrays.toString(iarr));
    }
}

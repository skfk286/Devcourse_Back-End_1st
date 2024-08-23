package com.ycjung.learn.grammar.coding_tip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypeConvert {

    public static void main(String[] args) {
        // case1();
        case2();
    }

    public static void case2() {
        Integer[] IntegerArray = {1, 2, 3, 4, 5};

        List<Integer> list = Arrays.asList(IntegerArray);
        System.out.println(list);

    }

    public static void case1() {
        // int[] 배열 생성
        int[] intArray = {1, 2, 3, 4, 5};

        // int[] -> ArrayList<Integer> 변환
        List<Integer> arrayList = Arrays.stream(intArray)  // IntStream으로 변환
                .boxed()  // Stream<Integer>로 변환
                .collect(Collectors.toList());  // List<Integer>로 수집

        // ArrayList<Integer> 출력
        System.out.println("ArrayList: " + arrayList);

        // ArrayList<Integer> -> int[] 변환
        int[] newArray = arrayList.stream()  // Stream<Integer>로 변환
                .mapToInt(Integer::intValue)  // IntStream으로 변환
                .toArray();  // int[] 배열로 변환

        // 변환된 int[] 배열 출력
        System.out.println("int[] array: " + Arrays.toString(newArray));
    }
}

package com.ycjung.learn.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_N03_두_개_뽑아서_더하기 {

    // 나의 문제풀이
    public static int[] solution(int[] numbers) {
        int[] answer = {};

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {

                int add = numbers[i] + numbers[j];

                if (!list.contains(add)) {
                    list.add(add);
                }
            }
        }

        answer = list.stream().sorted().
                mapToInt(Integer::intValue).toArray();

        return answer;
    }

//    public static int[] solution(int[] numbers) {
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = i + 1; j < numbers.length; j++) {
//                set.add(numbers[i] + numbers[j]);
//            }
//        }
//
//        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
//    }

    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 4, 1};
//        int[] numbers = {5,0,2,7};

        int[] result = solution(numbers);

        System.out.println(Arrays.toString(result));
    }
}

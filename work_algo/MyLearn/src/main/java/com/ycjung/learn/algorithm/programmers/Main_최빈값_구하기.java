package com.ycjung.learn.algorithm.programmers;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main_최빈값_구하기 {
    public static int solution(int[] array) {

        Map<Integer, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < array.length; i++) {
            if(map.containsKey(array[i])) {
                int count = map.get(array[i]);
                map.put(array[i], count + 1);
            } else {
                map.put(array[i], 1);
            }
        }

        int maxKey = -1;
        int maxValue = -1;
        for (int key : map.keySet()) {
            if(map.get(key) > maxValue) {
                maxValue = map.get(key);
                maxKey = key;
            }
        }

        int count = 0;
        for (int key : map.keySet()) {
            if(map.get(key) == maxValue) {
                count++;
            }
        }

        return count > 1 ? -1 : maxKey;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 3, 3, 4}));
        System.out.println(solution(new int[]{1, 1, 2, 2}));
        System.out.println(solution(new int[]{1}));
        System.out.println(solution(new int[]{1, 1, 2, 3}));
    }
}

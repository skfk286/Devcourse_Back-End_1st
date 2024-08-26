package com.ycjung.learn.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class Main_N04_모의고사 {
    // 나의 코드
//    public static int[] solution(int[] answers) {
//        int problemLength = answers.length;
//        System.out.println("length : " + problemLength);
//        int[] supoja1 = new int[problemLength];
//        int[] supoja2 = new int[problemLength];
//        int[] supoja3 = new int[problemLength];
//
//        int[] supoja1Pick = {1, 2, 3, 4, 5};
//        int[] supoja2Pick = {2, 1, 2, 3, 2, 4, 2, 5};
//        int[] supoja3Pick = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
//
//        for(int i = 0; i < problemLength; i++) {
//            supoja1[i] = supoja1Pick[i % supoja1Pick.length];
//            supoja2[i] = supoja2Pick[i % supoja2Pick.length];
//            supoja3[i] = supoja3Pick[i % supoja3Pick.length];
//        }
//
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1, count(answers, supoja1));
//        map.put(2, count(answers, supoja2));
//        map.put(3, count(answers, supoja3));
//
//        System.out.println(map);
//
//        int max = map.values().stream().max((o1, o2) -> o1 >= o2 ? 1 : -1).get();
//
//        List<Integer> list = new ArrayList<>();
//        for(int key : map.keySet()) {
//            if(map.get(key) == max) {
//                list.add(key);
//            }
//        }
//
//        return list.stream().mapToInt(Integer::intValue).toArray();

//    }
    
//    public static int count(int[] answers, int[] supoja) {
//        int count = 0;
//
//        for(int i=0;i<answers.length;i++) {
//            if(answers[i] == supoja[i]) {
//                count++;
//            }
//        }
//
//        return count;
//    }

    // 해답
    public static int[] solution(int[] answers) {
        int[][] pattern = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        int[] scores = new int[3];

        for(int i=0;i< answers.length;i++) {
            for(int j=0;j<pattern.length;j++) {
                if(answers[i] == pattern[j][i % pattern[j].length]) {
                    scores[j]++;
                }
            }
        }

        int maxScore = Arrays.stream(scores).max().getAsInt();

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0;i<scores.length;i++) {
            if(scores[i] == maxScore) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5})));
        System.out.println(Arrays.toString(solution(new int[]{1,3,2,4,2})));

//        int max = Math.max(5, Math.max(6, 8));
//        System.out.println("MAX : " + max);
    }
}

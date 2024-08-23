package com.ycjung.learn.algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main_N06_실패율 {

    // 3번, 10번 실패, 11번(런타임 에러) 
    // 가장 처음에 짯던 코드
//    public static int[] solution(int N, int[] stages) {
//        int[] answer = {};
//        Map<Integer, Double> map = new TreeMap<>();
//
//        for (int i = 1; i <= N; i++) {
//            final int stageNumber = i;
//
//            int nonePlayerCount = Arrays.stream(stages).filter(value -> stageNumber == value).toArray().length;
//            int completedPlayerCount = Arrays.stream(stages).filter(value -> stageNumber <= value).toArray().length;
//
//            double fail = (double)nonePlayerCount/completedPlayerCount;
//            fail = Double.isNaN(fail) ? 0.0d : fail;
//
//            System.out.println(nonePlayerCount + " , " + completedPlayerCount + " , " + fail);
//
//            map.put(stageNumber, fail);
//        }
//
//        answer = map.entrySet().stream()
//                .sorted((o1, o2) -> o1.getValue() <= o2.getValue() ? 1 : -1)
//                .map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();
//
//        return answer;
//    }

    // 3번, 10번 실패, 11번(런타임 에러)
    // 개선 코드
    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        Map<Integer, Double> map = new TreeMap<>();

        for (int i = 1; i <= N; i++) {
            final int stageNumber = i;

            int nonePlayerCount = Arrays.stream(stages).filter(value -> stageNumber == value).toArray().length;
            int completedPlayerCount = Arrays.stream(stages).filter(value -> stageNumber <= value).toArray().length;

            double fail = (double)nonePlayerCount/completedPlayerCount;
            fail = Double.isNaN(fail) ? 0.0d : fail;

            System.out.println(nonePlayerCount + " , " + completedPlayerCount + " , " + fail);

            map.put(stageNumber, fail);
        }

        answer = map.entrySet().stream()
                    .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                    .mapToInt(Map.Entry::getKey).toArray();

        return answer;
    }

    public static int[] solution2(int N, int[] stages) {
        int[] challenger = new int[N + 2];

        for(int i=0; i<stages.length; i++) {
            challenger[stages[i]] += 1;
        }

        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for(int i=1; i<=N; i++) {
            if(challenger[i] == 0) {
                fails.put(i, 0.);
            } else {
                fails.put(i, challenger[i] / total);
                total -= challenger[i];
            }
        }

        return fails.entrySet().stream()
                    .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                    .mapToInt(HashMap.Entry::getKey)
                    .toArray();
    }

    public static void main(String[] args) {
        int[] arr = solution(5, new int[]{2,1,2,6,2,4,3,3}); // result : [3,4,2,1,5]
        // int[] arr = solution(4, new int[]{4,4,4,4,4}); // result : [4,1,2,3]
        //int[] arr = solution(5, new int[]{1,1,1,2,3,4}); // result : [4, 1, 3, 2, 5] -> Nan 처리 이슈.


        System.out.println(Arrays.toString(arr));
    }
}

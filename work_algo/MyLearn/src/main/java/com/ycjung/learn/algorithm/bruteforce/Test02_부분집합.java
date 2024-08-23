package com.ycjung.learn.algorithm.bruteforce;

import java.util.Arrays;

/**
 * 부분집합 알고리즘 문제 재귀
 */
public class Test02_부분집합 {
    static int N = 3;
    static String[] cards = {"A", "B", "C"};
    static boolean[] select = new boolean[N];

    /**
     *
     * @param idx : 써볼 개수 A 또는 B 또는 C
     */
    static void subset(int idx) {
        if(idx == N) { // 옆에 카드 다 선택 될지 말지 먼저 재귀들이 골랐고.. 나는 카드 없음.
            System.out.println(Arrays.toString(select)); // N개 중에 true 라고 되어있는 것들로 시뮬레이션 돌려서 계산해보기.
            return;
        }

        select[idx] = true; // 나 이거 쓸게요.
        subset(idx + 1);
        select[idx] = false; // 끝났다면 상태 변경
        subset(idx + 1);
        return;
    }

    public static void main(String[] args) {
        subset(0);
    }
}

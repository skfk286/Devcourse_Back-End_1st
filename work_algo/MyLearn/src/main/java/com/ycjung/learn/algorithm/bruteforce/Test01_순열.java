package com.ycjung.learn.algorithm.bruteforce;

import java.util.Arrays;

/**
 * 순열 알고리즘 문제 재귀
 */
public class Test01_순열 {
    static int N=5, R=3;
    static String[] cards = {"A", "B","C", "D", "E"}; // 순열의 재료가 되는 대상(뽑히는 이들)
    static boolean[] used = new boolean[N];

    static String[] result = new String[R]; // N 개중에 R개를 뽑아서 순서까지 고려해서 채워놓을 배열

    /**
     * 순열 재귀.
     * @param idx
     */
    static void perm(int idx) {
        if(idx == R) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = 0; i < N; i++) { // 재귀가 한번 호출되면 A부터 E까지 모든 카드를 한번씩 다 보고 끝남
            if (used[i])
                continue;
            result[idx] = cards[i]; // 먼저 호출된 재귀가 안쓰는 카드네? 그럼 내가 맡은 자리에 놔볼게요.
            used[i] = true; // i번 카드 내가 쓰고 있다고 내 다음 재귀들한테 알려야 한다.
            perm(idx + 1); // 자 이제 얘가 스텍에서 나갈때까지 나는 이 아래 문장 실행 안하고 계속 기다린다.

            used[i] = false;
        }
    }

    public static void main(String[] args) {
        perm(0);
    }
}

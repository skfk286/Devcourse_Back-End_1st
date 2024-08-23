package com.ycjung.learn.algorithm.bruteforce;

import java.util.Arrays;

public class Test03_조합_for {
    static int N=5, R=3;
    static String[] cards = {"A", "B", "C", "D", "E"}; // 순열의 재료가 되는 대상(뽑히는 이들)
    static String[] result = new String[R];

    static void comb(int cnt, int start) {
        if(cnt == R) { // R개가 뽑아서 result 배열에 채워져있음!
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i=start; i<N; i++) { // int i=start 를 int i=0 으로 실수 하는 경우가 있다.
            result[cnt] = cards[i];
            comb(cnt+1, i+1); // i+1 부분을 'start' 라고 실수 하는 경우가 많다.
        }
    }

    public static void main(String[] args) {
        comb(0,0);
    }
}

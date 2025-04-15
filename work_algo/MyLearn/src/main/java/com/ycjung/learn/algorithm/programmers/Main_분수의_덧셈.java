package com.ycjung.learn.algorithm.programmers;

import java.util.Arrays;

public class Main_분수의_덧셈 {
    public static int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int numer = (numer1 * denom2) + (numer2 * denom1);
        int denom = denom1 * denom2;

        int i = 2;
        while (i <= denom) {
            if(numer % i == 0 && denom % i == 0) { // 기약 분수 처리 확실하게.
                numer = numer / i;
                denom = denom / i;
            } else {
                i = i + 1;
            }
        }

        return new int[]{numer, denom};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(1, 2, 3, 4)));
        System.out.println(Arrays.toString(solution(9, 2, 1, 3)));
        System.out.println(Arrays.toString(solution(997, 997, 997, 997)));
        System.out.println(Arrays.toString(solution(3, 1, 5, 1)));
        System.out.println(Arrays.toString(solution(555, 4, 999, 4)));
        System.out.println(Arrays.toString(solution(111, 111, 222, 222)));
        System.out.println(Arrays.toString(solution(110, 222, 111, 222)));
        System.out.println(Arrays.toString(solution(1, 1, 1, 1)));
    }
}

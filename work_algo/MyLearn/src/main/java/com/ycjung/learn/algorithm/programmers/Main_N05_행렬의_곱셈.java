package com.ycjung.learn.algorithm.programmers;

import java.util.Arrays;

public class Main_N05_행렬의_곱셈 {

    // 나의 코드
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int arr1Row = arr1.length;
        int arr1Col = arr1[0].length;
        int arr2Row = arr2.length;
        int arr2Col = arr2[0].length;
        // System.out.println("row : " + arr1Row + ", col : " + arr2Col);
        int[][] answer = new int[arr1Row][arr2Col];

        // 내가 짠 것..
        /*
        for (int i = 0; i < arr1Row; i++) {
            for (int j = 0; j < arr2Col; j++) {
                for (int x = 0; x < arr1Col; x++) {
                    for (int y = 0; y < arr2Row; y++) {
                        if (x == y) {
                            // System.out.println("i = " + i + ", j = " + j + " += " + (arr1[i][x] * arr2[y][j]));
                            answer[i][j] += (arr1[i][x] * arr2[y][j]);
                        }
                    }
                }
            }
        }
        */
        
        // 3중 for로 개선 가능
        for (int i = 0; i < arr1Row; i++) {
            for (int j = 0; j < arr2Col; j++) {
                for (int x = 0; x < arr1Col; x++) {
                    answer[i][j] += (arr1[i][x] * arr2[x][j]);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1 = {
                {1,4},
                {3,2},
                {4,1}
        };

        int[][] arr2 = {
                {3,3},
                {3,3}
        };

//        int[][] arr1 = {
//                {2, 3, 2},
//                {4, 2, 4},
//                {3, 1, 4}
//        };
//
//        int[][] arr2 = {
//                {5, 4, 3},
//                {2, 4, 1},
//                {3, 1, 1}
//        };

        System.out.println(Arrays.deepToString(solution(arr1, arr2)));
    }
}

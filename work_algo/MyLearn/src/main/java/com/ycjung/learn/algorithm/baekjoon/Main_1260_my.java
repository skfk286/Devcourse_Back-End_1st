package com.ycjung.learn.algorithm.baekjoon;

public class Main_1260_my {

    public static void main(String[] args) {
//        4 5 1
//        1 2
//        1 3
//        1 4
//        2 4
//        3 4
        String[] input = {
          "4 5 1",
          "1 2",
          "1 3",
          "1 4",
          "2 4",
          "3 4"
        };

        operation(input);
    }

    public static void operation(String[] input) {
        int N = Integer.parseInt(input[0].split(" ")[0]); // 정점의 개수
        int M = Integer.parseInt(input[0].split(" ")[1]); // 간선의 개수
        int start = Integer.parseInt(input[0].split(" ")[2]); // 탐색을 시작할 정점 번호
        int[][] arrays = new int[N][N];

        System.out.println("N:" + N);
        System.out.println("M:" + M);
        System.out.println("start:" + start);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arrays[i][j] = 0;
            }
        }

        print(arrays);

        for (int i = 1; i < input.length; i++) {
            String[] nodes = input[i].split(" ");

            for (int j = 0; j < nodes.length; j++) {
                int index = Integer.parseInt(nodes[j]);
                arrays[i - 1][index - 1] = 1;
            }
        }

        print(arrays);


    }

    public static void print(int[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + " ");
            }
            System.out.println();
        }
    }
}

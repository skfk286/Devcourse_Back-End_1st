package com.ycjung.learn.algorithm.baekjoon.day_0809_탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 섬의 개수
 */
public class Main_4963_섬의개수_my {
    public static final int[] di = {-1, -1, -1, 0, 1, 1,  1,  0};
    public static final int[] dj = {-1,  0,  1, 1, 1, 0, -1, -1};

    public static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String tmp = "";
        while((tmp = bf.readLine())!= null){
            int n = Integer.parseInt(tmp.split(" ")[0]);
            int m = Integer.parseInt(tmp.split(" ")[1]);

            if(n == 0 && m == 0) // 0 0 입력시 종료
                break;

            int[][] map = new int[m][n];
            visit = new boolean[m][n];
            int land = 0;

            int i = 0;
            while((tmp = bf.readLine())!= null){
                String[] tmpSplit = tmp.split(" ");
                for(int j = 0; j < tmpSplit.length; j++) {
                    map[i][j] = Integer.parseInt(tmpSplit[j]);
                }
                i++;

                if(m == i) break;
            }

            for(int row = 0; row < m ; row++) {
                for(int col = 0; col < n; col++) {
                    if(map[row][col] == 1 && !visit[row][col]) {
                        land++;

                        searchLandCount(map, row, col);
                    }
                }
            }

            System.out.println(land);
        }
    }

    public static void searchLandCount(int[][] map, int nowi, int nowj) {
        visit[nowi][nowj] = true;

        for(int i = 0; i < 8; i++) {
            int nexti = nowi + di[i];
            int nextj = nowj + dj[i];

            if(nexti >= 0 && nexti < map.length && nextj >= 0 && nextj < map[0].length &&
                    map[nexti][nextj] == 1 && !visit[nexti][nextj]) {
                searchLandCount(map, nexti, nextj);
            }
        }
    }
}

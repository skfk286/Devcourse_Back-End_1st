package com.ycjung.learn.algorithm.swea;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1289_my {
}

class Solution
{
    /**
        입력
        2
        0011
        100
     */
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        String[] inputArr = new String[T];
        for(int i = 0; i < T; i++) {
            Scanner sc2 = new Scanner(System.in);
            String inputMem = sc2.next();
            inputArr[i] = inputMem;
        }

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int count = 0;
            String mem = inputArr[test_case - 1];
            int memLength = mem.length();
            String[] memWork = new String[memLength];
            Arrays.fill(memWork, "0"); // 배열 값 모두 "0" 으로 초기화
            String[] memArr = mem.split("");

            while(!Arrays.equals(memWork, memArr)) {
                for(int i=0; i<memArr.length; i++) {
                    if(!memWork[i].equals(memArr[i])) {
                        memWork[i] = memArr[i];
                        for(int j=i+1; j<memArr.length; j++) {
                            memWork[j] = memArr[i];
                        }
                        break;
                    }
                }
                count ++;
            }

            System.out.println("#" + test_case + " " + count);
        }
    }
}
package com.ycjung.learn.algorithm.programmers;

public class Main_두_원_사이의_정수쌍 {

    public static long solution_solve_1(int r1, int r2) {
        long answer = 0;

        int count = 0;


        System.out.println(count);

        return answer;
    }

    // 테스트 케이스는 통과하나,
    // 런타임 오류 발생 코드
    public static long solution_solve_2(int r1, int r2) {
        long answer = 0;
        for (int i = -r2; i <= r2; i++) {
            for (int j = -r2; j <= r2; j++) {
                double result = Math.pow(i, 2) + Math.pow(j, 2);
                if (Math.pow(r1, 2) <= result && result <= Math.pow(r2, 2)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static long solution_solve_55(int r1, int r2) {
        long answer = 0;

        double r1pow = Math.pow(r1, 2);
        double r2pow = Math.pow(r2, 2);

        int onLine = (r2 - r1 + 1)*4; //선 위에 있는 좌표 개수

        for(int i=0; i<= r2; i++){ //1사분면만 계산
            double xpow = Math.pow(i, 2);


            if(i > r1) r1pow = 0;
            double y1 =0;
            if(r1pow != 0){
                y1 = Math.sqrt(r1pow - xpow); //작은원
                if(y1 > Math.floor(y1)){
                    y1 = Math.ceil(y1);
                }
            }

            double y2 = Math.sqrt(r2pow - xpow); //큰원
            if(y2 > Math.floor(y2)){
                y2 = Math.floor(y2);
            }
            answer += (int)y2 - (int)y1 + 1;

        }

        return answer*4 - onLine; //중복 계산된 점 빼주기
    }

    public static long solution_solve_3(int r1, int r2) {
        long answer = 0;

        // r^2 = x^2 + y^2
        long r1Pow = (long) Math.pow(r1, 2);
        long r2Pow = (long) Math.pow(r2, 2);

        for (long x = 1; x <= r2; x++) {
            System.out.println("x : " + x);
            long xPow = (long) Math.pow(x, 2);

            double y1 = Math.sqrt(r1Pow - xPow);

            if(y1 > Math.floor(y1)) {

            }


            double y2 = Math.sqrt(r2Pow - xPow);

//            System.out.println("firstY: " + firstY);
//            System.out.println("secondY: " + secondY);

            long min = (long) Math.floor(y1);
            long max = (long) Math.floor(y2);

//            System.out.println("min : " + min);
//            System.out.println("max : " + max);


            if(max == 0) {
                answer += 1;
                continue;
            }

            if(min == 0) {
                answer ++;
            }

            answer += (max - min);
//            System.out.println("answer : " + answer);
        }

//        System.out.println("count : " + count);

        answer *= 4;

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution_solve_3(2, 3));
    }
}

package com.ycjung.learn.algorithm.programmers;

// LV0
public class Main_369게임 {

    public static int solution(int order) {

        String orderStr = String.valueOf(order);

        char[] orderArr = orderStr.toCharArray();

        int count = 0;
        for (char c : orderArr) {
            int intC = Integer.valueOf(c + "");
            if (intC % 3 == 0 && intC != -1 && intC != 0) { // 숫자가 0 일 때도 있으니 제외해줘야 된다!
                count++;
            }
        }


        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
        System.out.println(solution(29423));
    }
}

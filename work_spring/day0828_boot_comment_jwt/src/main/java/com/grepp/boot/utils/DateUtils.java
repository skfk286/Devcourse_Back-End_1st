package com.grepp.boot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 현재 시간을 "yyyy-MM-dd HH:mm:ss" 형식으로 반환하는 메서드
     * @return 현재 시간의 문자열 표현 (년-월-일 시:분:초)
     */
    public static String getCurrentTimeFormatted() {
        // 현재 시간을 나타내는 Date 객체 생성
        Date now = new Date();

        // 출력할 날짜 형식 정의 (년-월-일 시:분:초)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 현재 시간을 지정한 형식으로 포맷팅하여 문자열로 반환
        return formatter.format(now);
    }
}

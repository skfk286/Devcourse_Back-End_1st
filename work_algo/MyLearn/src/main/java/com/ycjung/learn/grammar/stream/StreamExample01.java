package com.ycjung.learn.grammar.stream;

import com.ycjung.learn.grammar.Student;

import java.util.ArrayList;
import java.util.List;

public class StreamExample01 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("홍길동", 19, 80));
        list.add(new Student("홍길자", 25, 99));
        list.add(new Student("홍길순", 26, 35));
        list.add(new Student("홍길진", 21, 22));

        // 스트림으로 평균 구하기.
        double avg = list.stream()
                        .mapToInt(student -> student.getScore())
                        .average()
                        .getAsDouble();
        System.out.println("평균 : " + avg);

        // 스트림으로 Student 의 이름만 출력.
        list.stream()
                .map(student -> student.getName())
                .forEach(name -> System.out.println(name));



    }
}

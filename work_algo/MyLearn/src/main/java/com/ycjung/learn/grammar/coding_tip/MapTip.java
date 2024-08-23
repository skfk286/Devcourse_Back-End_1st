package com.ycjung.learn.grammar.coding_tip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapTip {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Tom", "Jerry", "Alice");

        // 스트림 생성 및 중간 연산 정의
        Stream<String> nameStream = names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("J");
                })
                .map(name -> {
                    System.out.println("Mapping: " + name);
                    return name.toUpperCase();
                });

        System.out.println("Stream pipeline defined, but not executed yet.");

        // 최종 연산 호출
        nameStream.forEach(name -> System.out.println("Final output: " + name));
    }
}

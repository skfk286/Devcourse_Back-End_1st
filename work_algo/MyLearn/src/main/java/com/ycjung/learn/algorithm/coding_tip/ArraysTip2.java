package com.ycjung.learn.algorithm.coding_tip;

import java.util.HashMap;
import java.util.Map;

public class ArraysTip2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("111","11111");
        map.put("222","22222");
        map.put("333","33333");

        map.keySet().forEach(s -> System.out.println(s));
        map.values().forEach(System.out::println);
    }
}

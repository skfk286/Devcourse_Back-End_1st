package com.ycjung.learn.grammar.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_List {
    public static List<String> list = Arrays.asList("aaa", "ccc", "ddd", "bbb");

    public static List<String> list2 = new ArrayList<>(Arrays.asList("aaa", "ccc", "ddd", "bbb"));

    public static void main(String[] args) {

        List list3 = new ArrayList();

        list3.add("aaa");
        list3.add(1);

        System.out.println(list3);
    }
}

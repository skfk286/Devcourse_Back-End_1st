package com.ycjung.learn.grammar.collection;

import java.util.ArrayList;
import java.util.List;

public class Main_VectorExample {
    public static void main(String[] args) {
        // List<Board> list = new Vector<>(); // 스레드 안전
        List<Board> list = new ArrayList<>(); // 스레드 불안전


        Thread threadA = new Thread(() -> {
           for(int i=1; i<=1000; i++) {
                list.add(new Board("제목"+i, "내용"+i, "글쓴이"+i));
           }
        });

        Thread threadB = new Thread(() -> {
            for(int i=1001; i<=2000; i++) {
                list.add(new Board("제목"+i, "내용"+i, "글쓴이"+i));
            }
        });

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (Exception e) { }

        int size = list.size();
        System.out.println("총 객체 수: " + size);
        System.out.println();
    }
}

class Board {
    private String subject;
    private String content;
    private String writer;

    public Board(String subject, String content, String writer) {
        this.subject = subject;
        this.content = content;
        this.writer = writer;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }
}
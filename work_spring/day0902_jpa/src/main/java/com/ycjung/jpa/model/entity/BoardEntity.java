package com.ycjung.jpa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

// 데이터 파일 그대로 데이터베이스 테이블이 된다.
@Entity(name = "board_tb")
public class BoardEntity {
    @Id // pk로 설정할 컬럼에게 붙여준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 설정
    private int no;
    private String title;
    private String content;
    private String writer;
    private int readCount;

    @CreationTimestamp // 쿼리가 실행되는 시점에 자동 시간 설정
    private String regDate;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "BoardEntity{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", readCount=" + readCount +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}

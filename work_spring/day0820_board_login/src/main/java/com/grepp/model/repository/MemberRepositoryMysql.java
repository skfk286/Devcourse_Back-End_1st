package com.grepp.model.repository;

import java.sql.SQLException;

public class MemberRepositoryMysql implements MemberRepository {

    private static MemberRepositoryMysql instance = new MemberRepositoryMysql();

    public static MemberRepositoryMysql getInstance() {
        return instance;
    }

    private MemberRepositoryMysql() {}

    @Override
    public String selectOne(String userid, String userpw) throws SQLException {
        // 원래는 여기서 DB 작업이 필요.
        if("programmers".equals(userid) && "1234".equals(userpw)) {
            return userid;
        }
        return null;
    }
}

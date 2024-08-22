package com.ycjung.model.repository;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class MemberRepositoryMysql implements MemberRepository {

    @Override
    public String selectOne(String userid, String userpw) throws SQLException {
        // 원래는 여기서 DB 작업이 필요.
        if("programmers".equals(userid) && "1234".equals(userpw)) {
            return userid;
        }
        return null;
    }
}

package com.grepp.model.repository;

import java.sql.SQLException;

public interface MemberRepository {
    public String selectOne(String userid, String userpw) throws SQLException;
}

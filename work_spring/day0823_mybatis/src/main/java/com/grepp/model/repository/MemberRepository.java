package com.grepp.model.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface MemberRepository {
    String selectOne(@Param("userid") String userid, @Param("userpw") String userpw) throws SQLException;
}

package com.grepp.boot.model.repository;

import com.grepp.boot.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface MemberRepository {
    MemberDTO selectOne(@Param("userid") String userid, @Param("userpw") String userpw) throws SQLException;
}

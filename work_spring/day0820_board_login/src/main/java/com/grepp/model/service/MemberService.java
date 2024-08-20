package com.grepp.model.service;

import com.grepp.model.repository.MemberRepository;
import com.grepp.model.repository.MemberRepositoryMysql;

import java.sql.SQLException;

public class MemberService {
    private static MemberService memberService = new MemberService();

    private MemberRepository repo = MemberRepositoryMysql.getInstance();

    private MemberService() {}
    public static MemberService getInstance() {
        return memberService;
    }

    public String login(String userid, String userpw) throws SQLException {
        return repo.selectOne(userid,userpw);
    }
}

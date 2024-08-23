package com.grepp.model.service;

import com.grepp.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public String login(String userid, String userpw) throws SQLException {
        return repo.selectOne(userid,userpw);
    }
}

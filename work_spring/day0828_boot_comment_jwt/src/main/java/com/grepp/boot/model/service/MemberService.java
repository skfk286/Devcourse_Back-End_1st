package com.grepp.boot.model.service;

import com.grepp.boot.model.dto.MemberDTO;
import com.grepp.boot.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public MemberDTO login(String userid, String userpw) throws SQLException {
        return repo.selectOne(userid,userpw);
    }
}

package com.grepp.jwt.model.service;

import com.grepp.jwt.model.entity.UserEntity;
import com.grepp.jwt.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity join(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity getByCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password); // 사용자 신원 조회
    }
}

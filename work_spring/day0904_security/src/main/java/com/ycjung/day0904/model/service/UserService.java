package com.ycjung.day0904.model.service;

import com.ycjung.day0904.model.entity.UserEntity;
import com.ycjung.day0904.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity join(UserEntity entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    public UserEntity getByCredentials(String username, String password) {
        UserEntity originalUser = userRepository.findByUsername(username);

        // 현재 로그인 시도에 사용된 패스워드 1234 와 회원가입시 저장된 패스워드 매칭 비교
        if (originalUser != null && passwordEncoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        
        return null;
    }
}

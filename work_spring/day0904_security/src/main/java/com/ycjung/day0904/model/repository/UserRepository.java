package com.ycjung.day0904.model.repository;

import com.ycjung.day0904.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsernameAndPassword(String username, String password);
}

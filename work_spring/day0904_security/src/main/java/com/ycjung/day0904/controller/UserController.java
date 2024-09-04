package com.ycjung.day0904.controller;

import com.ycjung.day0904.model.dto.UserDTO;
import com.ycjung.day0904.model.entity.UserEntity;
import com.ycjung.day0904.model.service.UserService;
import com.ycjung.day0904.util.MyJwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyJwtTokenProvider myJwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        if(userDTO == null || userDTO.getPassword() == null)
            throw new RuntimeException("Invalid Password!");

        UserEntity userEntity = userDTO.toEntity();
        UserEntity result = userService.join(userEntity);

        return ResponseEntity.ok().body(new UserDTO(result));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.getByCredentials(userDTO.getUsername(), userDTO.getPassword());

        if(userEntity != null) {
            String token = myJwtTokenProvider.createMyToken(userEntity, 1000 * 60 * 2); // ms 1000이 1초
            userDTO.setToken(token);

            return ResponseEntity.ok().body(userDTO); // Token 이 포함된 정보가 응답된다.
        }

        return ResponseEntity.badRequest().body("Login Failed!");
    }

}

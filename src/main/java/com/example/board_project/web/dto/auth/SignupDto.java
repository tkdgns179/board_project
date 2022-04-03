package com.example.board_project.web.dto.auth;

import com.example.board_project.domain.user.User;
import lombok.Data;

@Data
public class SignupDto {

    private String username;
    private String password;
    private String email;

    public  User toEntity() {
        return User.builder().username(username).password(password).email(email).build();
    }
}

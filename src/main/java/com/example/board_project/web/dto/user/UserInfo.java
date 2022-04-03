package com.example.board_project.web.dto.user;

import com.example.board_project.domain.user.User;
import lombok.Data;

@Data
public class UserInfo {
    private String username;
    private int id;

    public UserInfo(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
    }
}

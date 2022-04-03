package com.example.board_project.service;

import com.example.board_project.domain.user.User;
import com.example.board_project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(User user) {
        userRepository.save(user);
    }

}

package com.example.board_project.service;

import com.example.board_project.BoardProjectApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EmailServiceTest extends BoardProjectApplicationTests {

    @Autowired EmailService emailService;

    @Test
    public void mailSend() {
        emailService.mailCheck("tkdgns179@knou.ac.kr");
    }

}

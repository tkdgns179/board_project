package com.example.board_project.service;

import com.example.board_project.BoardProjectApplicationTests;
import com.example.board_project.domain.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BoardRepositoryTest extends BoardProjectApplicationTests {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

}

package com.example.board_project.service;

import com.example.board_project.domain.board.Board;
import com.example.board_project.domain.board.BoardRepository;
import com.example.board_project.domain.user.User;
import com.example.board_project.web.dto.board.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardDto boardDto, int userId) {
        Board boardEntity = new Board();
        User user = new User();
        user.setId(userId);
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setUser(user);
        boardEntity.setTitle(boardDto.getTitle());

        boardRepository.save(boardEntity);
        return boardEntity;
    }

}

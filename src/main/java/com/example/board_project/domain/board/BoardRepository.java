package com.example.board_project.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findByTitle(String title);
    Board findById(int id);

}

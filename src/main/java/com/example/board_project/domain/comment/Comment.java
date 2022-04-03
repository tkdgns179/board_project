package com.example.board_project.domain.comment;

import com.example.board_project.domain.board.Board;
import com.example.board_project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Comment { // N

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // 1

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}

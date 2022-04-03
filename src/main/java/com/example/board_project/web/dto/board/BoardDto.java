package com.example.board_project.web.dto.board;

import com.example.board_project.domain.board.Board;
import com.example.board_project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class BoardDto {
    private String title;
    private String content;
    private String[] files;

    public Board toEntity() {
        return Board.builder().title(title).content(content).build();
    }


}

package com.example.board_project.domain.image;

import com.example.board_project.domain.board.Board;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String postImageUrl;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "boardId")
    private Board board;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() { this.createDate = LocalDateTime.now(); }

}

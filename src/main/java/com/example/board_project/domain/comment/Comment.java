package com.example.board_project.domain.comment;

import com.example.board_project.domain.board.Board;
import com.example.board_project.domain.user.User;
import com.example.board_project.domain.utill.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Comment extends BaseTimeEntity { // N

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnoreProperties({"parent"})
    @JsonIgnore
    private List<Comment> childComments = new ArrayList<>();

    private boolean isRemoved = false;

    @ColumnDefault("0")
    @Max(5)
    private int level;

    @ColumnDefault("0")
    @Column(name = "group_id")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 1

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}

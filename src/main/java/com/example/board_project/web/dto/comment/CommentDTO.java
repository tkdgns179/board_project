package com.example.board_project.web.dto.comment;

import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer commentId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String content;
    private Integer groupId;
    private Boolean isRemoved;
    private Integer level;
    private Integer boardId;
    private Integer parentId;
    private Integer userId;
    private BigInteger hasParent;
    private Integer minLevel;

}

package com.example.board_project.service;

import com.example.board_project.domain.comment.Comment;
import com.example.board_project.domain.comment.CommentRepository;
import com.example.board_project.web.dto.comment.CommentDTO;
import com.example.board_project.web.dto.pagination.Criteria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final EntityManager em;

    @Transactional
    public List<CommentDTO> mComments(Criteria cri) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT a.comment_id commentId, a.createdDate, IFNULL(a.modifiedDate, a.createdDate) modifiedDate, a.content, a.group_id groupId, a.isRemoved,a.`level`, a.board_id boardId ");
        sb.append(", a.parent_id parentId, a.user_id userId ");
        sb.append(",(a.level - IFNULL(b.level, 1)) hasParent, d.min_level minLevel ");
        sb.append("FROM COMMENT a ");
        sb.append("LEFT OUTER JOIN comment b ON a.parent_id = b.comment_id ");
        sb.append("LEFT OUTER JOIN (SELECT a.group_id, MIN(a.level) min_level FROM (SELECT * FROM COMMENT LIMIT ? OFFSET ?) a GROUP BY group_id) d ON a.group_id = d.group_id ");
        sb.append("ORDER BY groupId, LEVEL ");
        sb.append("LIMIT ? OFFSET ? ");
        cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
        System.out.println("LiMIT : " + cri.getAmount() + " OFFSET : " + cri.getStartNum());

        Query query = em.createNativeQuery(sb.toString())
            .setParameter(1, cri.getAmount())
            .setParameter(2, cri.getStartNum())
            .setParameter(3, cri.getAmount())
            .setParameter(4, cri.getStartNum());

        JpaResultMapper result = new JpaResultMapper();
        List<CommentDTO> commentDTOList = result.list(query, CommentDTO.class);

        return commentDTOList;
    }

    @Transactional
    public Long totalComments() {
        return commentRepository.count();
    }

}

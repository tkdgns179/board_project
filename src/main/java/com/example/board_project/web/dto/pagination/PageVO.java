package com.example.board_project.web.dto.pagination;
// reference : https://moonhy7.tistory.com/entry/Spring-8%EC%9E%A5-%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%B2%98%EB%A6%AC

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
public class PageVO {
    private int startPage;
    private int endPage;
    private boolean prev, next;
    private Long total;

    private Criteria cri;

    public PageVO(Criteria cri, Long total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0) * 10);
        this.startPage = this.endPage - 9;

        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}

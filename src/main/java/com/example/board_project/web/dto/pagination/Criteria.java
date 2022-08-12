package com.example.board_project.web.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = "startNum")
@AllArgsConstructor
public class Criteria {
    private int pageNum;
    private int amount;
    private int startNum;

    public Criteria() {
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}

package com.example.board_project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CMRespDTO<T> {

    private int code; // 1 (OK) -1 (FAIL)
    private String message;
    private T data;

}

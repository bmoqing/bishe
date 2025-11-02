package com.univ.internship.dto;

import lombok.Data;

@Data
public class LogSubmitRequest {
    private Long studentId;
    private String content;
}

package com.univ.internship.dto;

import lombok.Data;

@Data
public class EvaluateRequest {
    private Long applicationId;
    private Double score;
    private String comment;
}

package com.univ.internship.dto;

import lombok.Data;

@Data
public class ComputeGradeRequest {
    private Long applicationId;
    private Double attendanceScore;
    private Double logScore;
}

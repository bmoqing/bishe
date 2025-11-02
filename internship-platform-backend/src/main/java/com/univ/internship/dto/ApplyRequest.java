package com.univ.internship.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplyRequest {
    @NotNull
    private Long studentId;
    @NotNull
    private Long positionId;
    private String reason;
}

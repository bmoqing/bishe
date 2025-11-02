package com.univ.internship.dto;

import lombok.Data;

@Data
public class CheckInRequest {
    private Long studentId;
    private Double lat;
    private Double lng;
    private String ip;
    private Long companyId; // 用于围栏验证
}

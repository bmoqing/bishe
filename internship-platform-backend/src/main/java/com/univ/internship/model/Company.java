package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Company {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String name;
    private String creditCode;
    private String address;
    private Double lat; // 企业位置（用于考勤地理围栏）
    private Double lng;
    private Boolean approved; // 管理员审核
}

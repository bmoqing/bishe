package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluations")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Evaluation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne @JoinColumn(name = "application_id")
    private InternshipApplication application;

    private Double companyScore; // 企业评价
    private String companyComment;

    private Double teacherScore; // 教师评分
    private String teacherComment;
}

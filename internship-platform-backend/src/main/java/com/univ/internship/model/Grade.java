package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grades")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne @JoinColumn(name = "application_id")
    private InternshipApplication application;

    private Double attendanceScore;
    private Double logScore;
    private Double companyScore;
    private Double teacherScore;
    private Double finalScore;
}

package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "internship_logs")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternshipLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate date;
    @Column(length = 2000)
    private String content;

    private Boolean reviewed;
    private String teacherComment;
}

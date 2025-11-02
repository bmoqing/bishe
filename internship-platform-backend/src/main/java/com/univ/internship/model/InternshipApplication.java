package com.univ.internship.model;

import com.univ.internship.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "internship_applications")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternshipApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne @JoinColumn(name = "position_id")
    private InternshipPosition position;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

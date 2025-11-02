package com.univ.internship.model;

import com.univ.internship.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_history")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "application_id")
    private InternshipApplication application;

    private Long auditorUserId;
    private String auditorName;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus toStatus;

    private String comment;
    private LocalDateTime time;
}

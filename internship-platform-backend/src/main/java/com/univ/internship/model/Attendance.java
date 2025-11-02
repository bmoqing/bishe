package com.univ.internship.model;

import com.univ.internship.model.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate date;
    private LocalDateTime checkInTime;
    private Double lat;
    private Double lng;
    private String ip;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private Double distanceMeter;
    private Boolean verified;
}

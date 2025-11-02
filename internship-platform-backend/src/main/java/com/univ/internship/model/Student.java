package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id
    private Long id; // same as user id

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String studentNo;
    private String major;
    private String clazz;
}

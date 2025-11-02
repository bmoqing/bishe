package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teachers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Teacher {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String titleName;
    private String department;
}

package com.univ.internship.model;

import com.univ.internship.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, length = 32)
    private RoleEnum code;

    private String name; // 展示名
}

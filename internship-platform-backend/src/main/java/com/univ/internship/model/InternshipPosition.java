package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "internship_positions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class InternshipPosition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "company_id")
    private Company company;

    private String title;
    private String description;
    private String city;
    private Double lat;
    private Double lng;
    private String period; // 周期描述，如 2025-03 ~ 2025-06
    private Boolean active;
}

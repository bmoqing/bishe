package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sys_params")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SysParam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 128)
    private String paramKey;

    @Column(length = 1024)
    private String paramValue;

    private String remark;
}

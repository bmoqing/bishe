package com.univ.internship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_operation_log")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SysOperationLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String username;
    private String action;
    private String uri;
    private String method;
    private String ip;
    private LocalDateTime time;
}

package com.univ.internship.dto;

import com.univ.internship.model.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String realName;
    @NotNull
    private RoleEnum role; // STUDENT/TEACHER/ADMIN/COMPANY
}

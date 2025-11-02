package com.univ.internship.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;      // 0 success, others error
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(0, "ok", data);
    }
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(0, "ok", null);
    }
    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<>(-1, msg, null);
    }
}

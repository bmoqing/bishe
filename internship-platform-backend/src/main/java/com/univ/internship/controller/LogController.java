package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.LogSubmitRequest;
import com.univ.internship.model.InternshipLog;
import com.univ.internship.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiResponse<InternshipLog> submit(@RequestBody LogSubmitRequest req) {
        return ApiResponse.ok(logService.submit(req.getStudentId(), req.getContent()));
    }

    @GetMapping("/my/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiResponse<List<InternshipLog>> my(@PathVariable Long studentId) {
        return ApiResponse.ok(logService.myLogs(studentId));
    }

    @PostMapping("/{id}/review")
    @PreAuthorize("hasRole('TEACHER')")
    public ApiResponse<InternshipLog> review(@PathVariable Long id, @RequestParam String comment) {
        return ApiResponse.ok(logService.review(id, comment));
    }
}

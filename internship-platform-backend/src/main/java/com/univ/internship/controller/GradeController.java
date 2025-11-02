package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.ComputeGradeRequest;
import com.univ.internship.model.Grade;
import com.univ.internship.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/compute")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ApiResponse<Grade> compute(@RequestBody ComputeGradeRequest req) {
        return ApiResponse.ok(gradeService.compute(req.getApplicationId(), req.getAttendanceScore(), req.getLogScore()));
    }
}

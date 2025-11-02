package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.EvaluateRequest;
import com.univ.internship.model.Evaluation;
import com.univ.internship.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eval")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    public ApiResponse<Evaluation> company(@RequestBody EvaluateRequest req) {
        return ApiResponse.ok(evaluationService.companyEvaluate(req.getApplicationId(), req.getScore(), req.getComment()));
    }

    @PostMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public ApiResponse<Evaluation> teacher(@RequestBody EvaluateRequest req) {
        return ApiResponse.ok(evaluationService.teacherEvaluate(req.getApplicationId(), req.getScore(), req.getComment()));
    }
}

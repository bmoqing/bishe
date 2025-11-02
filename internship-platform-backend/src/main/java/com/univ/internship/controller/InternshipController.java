package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.ApplyRequest;
import com.univ.internship.model.InternshipApplication;
import com.univ.internship.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internship")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiResponse<InternshipApplication> apply(@RequestBody @Valid ApplyRequest req) {
        return ApiResponse.ok(internshipService.apply(req.getStudentId(), req.getPositionId(), req.getReason()));
    }

    @PostMapping("/{id}/teacher-approve")
    @PreAuthorize("hasRole('TEACHER')")
    public ApiResponse<InternshipApplication> teacherApprove(@PathVariable Long id,
                                                             @RequestParam boolean pass,
                                                             @RequestParam(required = false) String comment,
                                                             @RequestParam Long teacherUserId,
                                                             @RequestParam String teacherName) {
        return ApiResponse.ok(internshipService.teacherApprove(id, teacherUserId, teacherName, pass, comment));
    }

    @PostMapping("/{id}/admin-record")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<InternshipApplication> adminRecord(@PathVariable Long id,
                                                          @RequestParam boolean pass,
                                                          @RequestParam(required = false) String comment,
                                                          @RequestParam Long adminUserId,
                                                          @RequestParam String adminName) {
        return ApiResponse.ok(internshipService.adminRecord(id, adminUserId, adminName, pass, comment));
    }

    @GetMapping("/my/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiResponse<List<InternshipApplication>> myApps(@PathVariable Long studentId) {
        return ApiResponse.ok(internshipService.myApps(studentId));
    }
}

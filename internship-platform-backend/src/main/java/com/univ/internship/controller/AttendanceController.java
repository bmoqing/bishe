package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.CheckInRequest;
import com.univ.internship.model.Attendance;
import com.univ.internship.model.Company;
import com.univ.internship.repo.CompanyRepository;
import com.univ.internship.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final CompanyRepository companyRepository;

    @PostMapping("/check-in")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiResponse<Attendance> checkIn(@RequestBody CheckInRequest req) {
        Company company = req.getCompanyId() == null ? null :
                companyRepository.findById(req.getCompanyId()).orElse(null);
        Attendance att = attendanceService.checkIn(req.getStudentId(), req.getLat(), req.getLng(), req.getIp(), company);
        return ApiResponse.ok(att);
    }
}

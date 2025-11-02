package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.model.SysOperationLog;
import com.univ.internship.model.SysParam;
import com.univ.internship.repo.SysOperationLogRepository;
import com.univ.internship.service.SysParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sys")
@RequiredArgsConstructor
public class SysController {

    private final SysParamService paramService;
    private final SysOperationLogRepository logRepo;

    @GetMapping("/param/{key}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<SysParam> getParam(@PathVariable String key) {
        return ApiResponse.ok(paramService.getByKey(key).orElse(null));
    }

    @PostMapping("/param/{key}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<SysParam> setParam(@PathVariable String key, @RequestBody Map<String, String> body) {
        String value = body.getOrDefault("value", "");
        String remark = body.getOrDefault("remark", "");
        return ApiResponse.ok(paramService.set(key, value, remark));
    }

    @GetMapping("/oplogs")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<SysOperationLog>> logs() {
        return ApiResponse.ok(logRepo.findAll());
    }
}

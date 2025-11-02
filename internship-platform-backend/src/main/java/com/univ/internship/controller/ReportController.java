package com.univ.internship.controller;

import com.univ.internship.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/grades.xlsx")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ResponseEntity<byte[]> excel() {
        byte[] bytes = reportService.exportGradesExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grades.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(bytes);
    }

    @GetMapping("/grades.pdf")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ResponseEntity<byte[]> pdf() {
        byte[] bytes = reportService.exportGradesPdf();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grades.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}

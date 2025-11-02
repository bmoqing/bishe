package com.univ.internship.service;

import com.univ.internship.model.*;
import com.univ.internship.model.enums.ApplicationStatus;
import com.univ.internship.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipApplicationRepository appRepo;
    private final InternshipPositionRepository posRepo;
    private final StudentRepository stuRepo;
    private final AuditHistoryRepository auditRepo;

    @Transactional
    public InternshipApplication apply(Long studentId, Long positionId, String reason) {
        Student stu = stuRepo.findById(studentId).orElseThrow();
        InternshipPosition pos = posRepo.findById(positionId).orElseThrow();
        InternshipApplication app = InternshipApplication.builder()
                .student(stu)
                .position(pos)
                .status(ApplicationStatus.SUBMITTED)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        appRepo.save(app);
        auditRepo.save(AuditHistory.builder()
                .application(app).auditorUserId(stu.getId()).auditorName(stu.getUser().getRealName())
                .toStatus(ApplicationStatus.SUBMITTED).comment("学生提交").time(LocalDateTime.now()).build());
        return app;
    }

    @Transactional
    public InternshipApplication teacherApprove(Long appId, Long teacherUserId, String teacherName, boolean pass, String comment) {
        InternshipApplication app = appRepo.findById(appId).orElseThrow();
        app.setStatus(pass ? ApplicationStatus.TEACHER_APPROVED : ApplicationStatus.TEACHER_REJECTED);
        app.setUpdatedAt(LocalDateTime.now());
        appRepo.save(app);
        auditRepo.save(AuditHistory.builder()
                .application(app).auditorUserId(teacherUserId).auditorName(teacherName)
                .toStatus(app.getStatus()).comment(comment).time(LocalDateTime.now()).build());
        return app;
    }

    @Transactional
    public InternshipApplication adminRecord(Long appId, Long adminUserId, String adminName, boolean pass, String comment) {
        InternshipApplication app = appRepo.findById(appId).orElseThrow();
        app.setStatus(pass ? ApplicationStatus.ADMIN_FILED : ApplicationStatus.ADMIN_REJECTED);
        app.setUpdatedAt(LocalDateTime.now());
        appRepo.save(app);
        auditRepo.save(AuditHistory.builder()
                .application(app).auditorUserId(adminUserId).auditorName(adminName)
                .toStatus(app.getStatus()).comment(comment).time(LocalDateTime.now()).build());
        return app;
    }

    public List<InternshipApplication> myApps(Long studentId) {
        return appRepo.findAll().stream().filter(a -> a.getStudent().getId().equals(studentId)).toList();
    }
}

package com.univ.internship.service;

import com.univ.internship.model.Evaluation;
import com.univ.internship.model.Grade;
import com.univ.internship.model.InternshipApplication;
import com.univ.internship.repo.EvaluationRepository;
import com.univ.internship.repo.GradeRepository;
import com.univ.internship.repo.InternshipApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {

    @Value("${app.score.weight.attendance}")
    private double wAttendance;
    @Value("${app.score.weight.log}")
    private double wLog;
    @Value("${app.score.weight.company}")
    private double wCompany;
    @Value("${app.score.weight.teacher}")
    private double wTeacher;

    private final GradeRepository gradeRepo;
    private final InternshipApplicationRepository appRepo;
    private final EvaluationRepository evaRepo;

    @Transactional
    public Grade compute(Long appId, double attendanceScore, double logScore) {
        InternshipApplication app = appRepo.findById(appId).orElseThrow();
        Evaluation eva = evaRepo.findAll().stream()
                .filter(e -> e.getApplication().getId().equals(appId)).findFirst()
                .orElse(null);
        double companyScore = eva != null && eva.getCompanyScore() != null ? eva.getCompanyScore() : 0;
        double teacherScore = eva != null && eva.getTeacherScore() != null ? eva.getTeacherScore() : 0;

        double finalScore = attendanceScore * wAttendance +
                logScore * wLog + companyScore * wCompany + teacherScore * wTeacher;

        Grade g = gradeRepo.findAll().stream()
                .filter(x -> x.getApplication().getId().equals(appId))
                .findFirst()
                .orElse(Grade.builder().application(app).build());
        g.setAttendanceScore(attendanceScore);
        g.setLogScore(logScore);
        g.setCompanyScore(companyScore);
        g.setTeacherScore(teacherScore);
        g.setFinalScore(finalScore);
        return gradeRepo.save(g);
    }
}

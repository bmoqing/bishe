package com.univ.internship.service;

import com.univ.internship.model.Evaluation;
import com.univ.internship.model.InternshipApplication;
import com.univ.internship.repo.EvaluationRepository;
import com.univ.internship.repo.InternshipApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final InternshipApplicationRepository appRepo;
    private final EvaluationRepository evaRepo;

    @Transactional
    public Evaluation companyEvaluate(Long appId, Double score, String comment) {
        InternshipApplication app = appRepo.findById(appId).orElseThrow();
        Evaluation eva = evaRepo.findAll().stream()
                .filter(e -> e.getApplication().getId().equals(appId)).findFirst()
                .orElse(Evaluation.builder().application(app).build());
        eva.setCompanyScore(score);
        eva.setCompanyComment(comment);
        return evaRepo.save(eva);
    }

    @Transactional
    public Evaluation teacherEvaluate(Long appId, Double score, String comment) {
        InternshipApplication app = appRepo.findById(appId).orElseThrow();
        Evaluation eva = evaRepo.findAll().stream()
                .filter(e -> e.getApplication().getId().equals(appId)).findFirst()
                .orElse(Evaluation.builder().application(app).build());
        eva.setTeacherScore(score);
        eva.setTeacherComment(comment);
        return evaRepo.save(eva);
    }
}

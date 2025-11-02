package com.univ.internship.service;

import com.univ.internship.model.InternshipLog;
import com.univ.internship.model.Student;
import com.univ.internship.repo.InternshipLogRepository;
import com.univ.internship.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final InternshipLogRepository logRepo;
    private final StudentRepository stuRepo;

    @Transactional
    public InternshipLog submit(Long studentId, String content) {
        Student stu = stuRepo.findById(studentId).orElseThrow();
        InternshipLog log = InternshipLog.builder()
                .student(stu)
                .date(LocalDate.now())
                .content(content)
                .reviewed(false)
                .build();
        return logRepo.save(log);
    }

    public List<InternshipLog> myLogs(Long studentId) {
        return logRepo.findAll().stream().filter(l -> l.getStudent().getId().equals(studentId)).toList();
    }

    @Transactional
    public InternshipLog review(Long logId, String comment) {
        InternshipLog log = logRepo.findById(logId).orElseThrow();
        log.setReviewed(true);
        log.setTeacherComment(comment);
        return logRepo.save(log);
    }
}

package com.univ.internship.service;

import com.univ.internship.common.GeoUtil;
import com.univ.internship.model.*;
import com.univ.internship.model.enums.AttendanceStatus;
import com.univ.internship.repo.AttendanceRepository;
import com.univ.internship.repo.CompanyRepository;
import com.univ.internship.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    @Value("${app.attendance.max-distance-meter}")
    private double maxDistanceMeter;

    private final AttendanceRepository attRepo;
    private final StudentRepository stuRepo;
    private final CompanyRepository companyRepo;

    @Transactional
    public Attendance checkIn(Long studentId, Double lat, Double lng, String ip, Company company) {
        Student stu = stuRepo.findById(studentId).orElseThrow();
        LocalDate today = LocalDate.now();
        Optional<Attendance> existed = attRepo.findAll().stream()
                .filter(a -> a.getStudent().getId().equals(studentId) && a.getDate().equals(today)).findFirst();
        if (existed.isPresent()) return existed.get();

        double distance = 999999d;
        boolean verified = false;
        if (company != null && company.getLat() != null && company.getLng() != null && lat != null && lng != null) {
            distance = GeoUtil.distanceMeters(lat, lng, company.getLat(), company.getLng());
            verified = distance <= maxDistanceMeter;
        }
        Attendance att = Attendance.builder()
                .student(stu)
                .date(today)
                .checkInTime(LocalDateTime.now())
                .lat(lat).lng(lng).ip(ip)
                .status(verified ? AttendanceStatus.NORMAL : AttendanceStatus.ABSENT)
                .distanceMeter(distance)
                .verified(verified)
                .build();
        return attRepo.save(att);
    }
}

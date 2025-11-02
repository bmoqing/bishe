package com.univ.internship.repo;

import com.univ.internship.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


}

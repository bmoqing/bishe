package com.univ.internship.repo;

import com.univ.internship.model.InternshipLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InternshipLogRepository extends JpaRepository<InternshipLog, Long> {


}

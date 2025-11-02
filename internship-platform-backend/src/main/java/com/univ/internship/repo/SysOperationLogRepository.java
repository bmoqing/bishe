package com.univ.internship.repo;

import com.univ.internship.model.SysOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SysOperationLogRepository extends JpaRepository<SysOperationLog, Long> {


}

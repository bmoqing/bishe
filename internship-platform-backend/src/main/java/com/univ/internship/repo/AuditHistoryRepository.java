package com.univ.internship.repo;

import com.univ.internship.model.AuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface AuditHistoryRepository extends JpaRepository<AuditHistory, Long> {


}

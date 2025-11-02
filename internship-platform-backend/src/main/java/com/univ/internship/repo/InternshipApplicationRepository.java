package com.univ.internship.repo;

import com.univ.internship.model.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {


}

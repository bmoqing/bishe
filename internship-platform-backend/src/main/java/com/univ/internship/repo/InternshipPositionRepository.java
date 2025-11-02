package com.univ.internship.repo;

import com.univ.internship.model.InternshipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InternshipPositionRepository extends JpaRepository<InternshipPosition, Long> {


}

package com.univ.internship.repo;

import com.univ.internship.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {


}

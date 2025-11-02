package com.univ.internship.repo;

import com.univ.internship.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


}

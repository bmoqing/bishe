package com.univ.internship.repo;

import com.univ.internship.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}

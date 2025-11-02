package com.univ.internship.repo;

import com.univ.internship.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {


}

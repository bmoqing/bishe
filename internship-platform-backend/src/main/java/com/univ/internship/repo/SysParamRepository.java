package com.univ.internship.repo;

import com.univ.internship.model.SysParam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SysParamRepository extends JpaRepository<SysParam, Long> {


}

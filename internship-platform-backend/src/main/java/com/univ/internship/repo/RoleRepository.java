package com.univ.internship.repo;

import com.univ.internship.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByCode(com.univ.internship.model.enums.RoleEnum code);
}

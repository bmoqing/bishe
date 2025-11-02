package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.model.Role;
import com.univ.internship.model.User;
import com.univ.internship.model.enums.RoleEnum;
import com.univ.internship.repo.RoleRepository;
import com.univ.internship.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<User>> users() {
        return ApiResponse.ok(userRepo.findAll());
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> create(@RequestParam String username, @RequestParam String password,
                                    @RequestParam String realName, @RequestParam RoleEnum role) {
        Role r = roleRepo.findByCode(role).orElseThrow();
        User u = User.builder().username(username).password(encoder.encode(password))
                .realName(realName).roles(Set.of(r)).build();
        return ApiResponse.ok(userRepo.save(u));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userRepo.deleteById(id);
        return ApiResponse.ok();
    }
}

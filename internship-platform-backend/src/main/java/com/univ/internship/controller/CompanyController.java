package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.model.Company;
import com.univ.internship.model.User;
import com.univ.internship.model.enums.RoleEnum;
import com.univ.internship.repo.CompanyRepository;
import com.univ.internship.repo.RoleRepository;
import com.univ.internship.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    // 企业自助注册（账号+企业信息），默认未审核
    @PostMapping("/register")
    public ApiResponse<Company> register(@RequestParam String username, @RequestParam String password,
                                         @RequestParam String realName, @RequestParam String companyName,
                                         @RequestParam String creditCode, @RequestParam String address,
                                         @RequestParam(required = false) Double lat,
                                         @RequestParam(required = false) Double lng) {
        User u = User.builder()
                .username(username).password(encoder.encode(password))
                .realName(realName)
                .roles(Set.of(roleRepo.findByCode(RoleEnum.COMPANY).orElseThrow()))
                .build();
        userRepo.save(u);
        Company c = Company.builder().user(u).name(companyName).creditCode(creditCode).address(address)
                .lat(lat).lng(lng).approved(false).build();
        return ApiResponse.ok(companyRepo.save(c));
    }

    // 管理员审核企业
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Company> approve(@PathVariable Long id, @RequestParam boolean pass) {
        Company c = companyRepo.findById(id).orElseThrow();
        c.setApproved(pass);
        return ApiResponse.ok(companyRepo.save(c));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ApiResponse<List<Company>> list() {
        return ApiResponse.ok(companyRepo.findAll());
    }
}

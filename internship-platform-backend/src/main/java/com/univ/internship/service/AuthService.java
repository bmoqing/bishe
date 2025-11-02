package com.univ.internship.service;

import com.univ.internship.model.Role;
import com.univ.internship.model.User;
import com.univ.internship.model.enums.RoleEnum;
import com.univ.internship.repo.RoleRepository;
import com.univ.internship.repo.UserRepository;
import com.univ.internship.security.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow();
        List<String> roles = user.getRoles().stream().map(r -> r.getCode().name()).toList();
        return jwtUtil.generateToken(username, roles);
    }

    @Transactional
    public User register(String username, String rawPassword, String realName, RoleEnum role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }
        Role r = roleRepository.findByCode(role).orElseThrow(() -> new RuntimeException("角色不存在"));
        User u = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .realName(realName)
                .roles(Set.of(r))
                .build();
        return userRepository.save(u);
    }
}

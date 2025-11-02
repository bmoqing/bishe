package com.univ.internship.service;

import com.univ.internship.model.SysOperationLog;
import com.univ.internship.repo.SysOperationLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class OpLogAspect {

    private final SysOperationLogRepository logRepository;
    private final HttpServletRequest request;

    @AfterReturning("execution(* com.univ.internship.controller..*(..))")
    public void log(JoinPoint jp) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth != null ? auth.getName() : "anonymous";
        SysOperationLog log = SysOperationLog.builder()
                .username(username)
                .action(jp.getSignature().toShortString())
                .uri(request.getRequestURI())
                .method(request.getMethod())
                .ip(request.getRemoteAddr())
                .time(LocalDateTime.now())
                .build();
        logRepository.save(log);
    }
}

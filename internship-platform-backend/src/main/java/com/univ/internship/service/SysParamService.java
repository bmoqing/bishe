package com.univ.internship.service;

import com.univ.internship.model.SysParam;
import com.univ.internship.repo.SysParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysParamService {
    private final SysParamRepository repo;

    public Optional<SysParam> getByKey(String key) {
        return repo.findAll().stream().filter(p -> p.getParamKey().equals(key)).findFirst();
    }

    @Transactional
    public SysParam set(String key, String value, String remark) {
        SysParam p = getByKey(key).orElse(SysParam.builder().paramKey(key).build());
        p.setParamValue(value);
        p.setRemark(remark);
        return repo.save(p);
    }
}

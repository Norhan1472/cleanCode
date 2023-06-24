package com.spring.gucms.service;

import com.spring.gucms.entity.Authorities;
import com.spring.gucms.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesService {
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    public AuthoritiesService(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }
    public List<Authorities> getAllAuthorities(){
        return authoritiesRepository.findAll();
    }
}

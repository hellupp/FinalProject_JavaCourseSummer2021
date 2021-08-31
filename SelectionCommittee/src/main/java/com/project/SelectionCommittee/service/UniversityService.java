package com.project.SelectionCommittee.service;

import com.project.SelectionCommittee.repository.RoleRepository;
import com.project.SelectionCommittee.repository.UniversityRepository;
import com.project.SelectionCommittee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {
    private UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }




}

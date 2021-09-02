package com.project.SelectionCommittee.service;


import com.project.SelectionCommittee.model.Faculty;
import com.project.SelectionCommittee.model.Role;
import com.project.SelectionCommittee.model.University;
import com.project.SelectionCommittee.model.User;
import com.project.SelectionCommittee.repository.FacultyRepository;
import com.project.SelectionCommittee.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> allFacultiesFrom(University university) {
        return facultyRepository.findAllByUniversity_id(university.getId());
    }

    public List<Faculty> allFaculties() {
        return facultyRepository.findAll();
    }

    public boolean deleteFaculty(Long facultyId) {
        if (facultyRepository.findById(facultyId).isPresent()) {
            facultyRepository.deleteById(facultyId);

            return true;
        }
        return false;
    }

    public Faculty saveFacultyTo(Faculty faculty, University university) {
        faculty.setUniversity_id(university.getId());
        return facultyRepository.save(faculty);
    }


    public Faculty saveFaculty(Faculty faculty){
        System.out.println(faculty.toString());
        return facultyRepository.save(faculty);
    }

    public List<Faculty> applicationsOfUser(User user){
        return facultyRepository.findAllByUser(user.getId());
    }





}

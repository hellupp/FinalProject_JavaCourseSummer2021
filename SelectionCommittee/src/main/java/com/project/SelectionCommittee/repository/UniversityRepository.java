package com.project.SelectionCommittee.repository;

import com.project.SelectionCommittee.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    @Query("SELECT u FROM University u WHERE u.universityName = ?1")
    University findByUniversityName(String universityName);
}

package com.project.SelectionCommittee.repository;

import com.project.SelectionCommittee.model.Faculty;
import com.project.SelectionCommittee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("SELECT f FROM Faculty f WHERE f.university_id = ?1")
    List<Faculty> findAllByUniversity_id(Long university_id);

    @Query("SELECT f FROM Faculty f WHERE f.id = ?1")
    Faculty findFacultyById(Long id);

    @Query("SELECT f FROM Faculty f JOIN f.users user WHERE user.id = ?1")
    List<Faculty> findAllByUser(Long id);

}

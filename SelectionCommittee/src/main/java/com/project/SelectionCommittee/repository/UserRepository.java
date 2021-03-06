package com.project.SelectionCommittee.repository;

import com.project.SelectionCommittee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findByUserId(Long id);
}
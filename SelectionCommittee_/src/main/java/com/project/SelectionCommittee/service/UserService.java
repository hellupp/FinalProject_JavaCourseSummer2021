package com.project.SelectionCommittee.service;

import com.project.SelectionCommittee.model.Role;
import com.project.SelectionCommittee.model.User;
import com.project.SelectionCommittee.repository.RoleRepository;
import com.project.SelectionCommittee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("APPLICANT");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean blockUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.findById(id).get().setActive(false);
            userRepository.save(userRepository.findById(id).get());
            return true;
        }

        return false;
    }

    public boolean unblockUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.findById(id).get().setActive(true);
            userRepository.save(userRepository.findById(id).get());
            return true;
        }

        return false;
    }
}
package com.project.SelectionCommittee.controller;

import com.project.SelectionCommittee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicantController {
    @Autowired
    private UserService userService;

    @GetMapping("/applicant/home")
    public String home() {
        return "applicantHome";
    }

    @PostMapping("/applicant/home")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "applicantHome";
    }

//    @GetMapping("/applicant/allUsers")
//    public String listStudent(Model model) {
//        model.addAttribute("users", userService.allUsers());
//        return "adminAllUsers";
//    }

}
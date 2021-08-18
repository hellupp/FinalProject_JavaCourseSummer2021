package com.project.SelectionCommittee.controller;

import com.project.SelectionCommittee.service.FacultyService;
import com.project.SelectionCommittee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/admin/home")
    public String home() {
        return "adminHome";
    }

    @GetMapping("/allUsers")
    public String listApplicants(Model model) {
        model.addAttribute("applicants", userService.allUsers());
        return "adminAllUsers";
    }

    @GetMapping("/delete/{id}")
    public String  deleteUser(@PathVariable(value = "id") Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("applicants", userService.allUsers());
        return "adminAllUsers";
    }

    @GetMapping("/block/{id}")
    public String  blockUser(@PathVariable(value = "id") Long id, Model model) {
        userService.blockUser(id);
        model.addAttribute("applicants", userService.allUsers());
        return "adminAllUsers";
    }

    @GetMapping("/unblock/{id}")
    public String  unblockUser(@PathVariable(value = "id") Long id, Model model) {
        userService.unblockUser(id);
        model.addAttribute("applicants", userService.allUsers());
        return "adminAllUsers";
    }

    @GetMapping("/allFaculties")
    public String listFaculties(Model model) {
        model.addAttribute("faculties", facultyService.allFaculties());
        return "adminAllFaculties";
    }

//    @GetMapping("/edit/{id}")
//    public String  editFaculty(@PathVariable(value = "id") Long id, Model model) {
//        model.addAttribute("applicants", userService.allUsers());
//        return "adminAllUsers";
//    }

    @GetMapping("/delete_faculty/{id}")
    public String  deleteFaculty(@PathVariable(value = "id") Long id, Model model) {
        facultyService.deleteFaculty(id);
        model.addAttribute("faculties", facultyService.allFaculties());
        return "adminAllFaculties";
    }

}

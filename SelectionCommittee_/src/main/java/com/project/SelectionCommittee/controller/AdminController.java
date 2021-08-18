package com.project.SelectionCommittee.controller;

import com.project.SelectionCommittee.model.Faculty;
import com.project.SelectionCommittee.model.User;
import com.project.SelectionCommittee.repository.FacultyRepository;
import com.project.SelectionCommittee.service.FacultyService;
import com.project.SelectionCommittee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;

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

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid faculty Id:" + id));

        model.addAttribute("faculty", faculty);
        return "editFaculty";
    }

    @PostMapping("/update/{id}")
    public String updateFaculty(@PathVariable("id") long id, @Valid Faculty faculty,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            faculty.setId(id);
            return "editFaculty";
        }

        facultyRepository.save(faculty);
        model.addAttribute("faculties", facultyService.allFaculties());
        return "adminAllFaculties";
    }

    @GetMapping("/delete_faculty/{id}")
    public String  deleteFaculty(@PathVariable(value = "id") Long id, Model model) {
        facultyService.deleteFaculty(id);
        model.addAttribute("faculties", facultyService.allFaculties());
        return "adminAllFaculties";
    }

    @GetMapping(value="/add")
    public ModelAndView adding(){
        ModelAndView modelAndView = new ModelAndView();
        Faculty faculty = new Faculty();
        modelAndView.addObject("faculty", faculty);
        modelAndView.setViewName("addFaculty");
        return modelAndView;
    }

    @PostMapping("/add_faculty")
    public ModelAndView  addFaculty(@Valid Faculty faculty, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if(!result.hasErrors()){
            facultyService.saveFaculty(faculty);
            modelAndView.addObject("faculty", new Faculty());
        }

        modelAndView.addObject("faculties", facultyService.allFaculties());
        modelAndView.setViewName("adminAllFaculties");
        return modelAndView;
    }

}

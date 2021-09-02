package com.project.SelectionCommittee.controller;

import com.project.SelectionCommittee.model.Faculty;
import com.project.SelectionCommittee.model.University;
import com.project.SelectionCommittee.model.User;
import com.project.SelectionCommittee.repository.FacultyRepository;
import com.project.SelectionCommittee.repository.UniversityRepository;
import com.project.SelectionCommittee.repository.UserRepository;
import com.project.SelectionCommittee.service.FacultyService;
import com.project.SelectionCommittee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
public class ApplicantController {
    @Autowired
    private UserService userService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/applicant/home")
    public String home() {
        return "applicantHome";
    }

//    @RequestMapping(value = "/showFaculties", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }

    @GetMapping("/showFaculties")
    @ResponseBody
    public ModelAndView listFaculties(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUserName(principal.getName());
        University university = universityRepository.findByUniversityName(user.getInstitutionName());
        modelAndView.addObject("faculties", facultyService.allFacultiesFrom(university));
        modelAndView.addObject("user", user);
        modelAndView.setViewName("applicantAllFaculties");
        return modelAndView;
    }



    @GetMapping("/reg/{id}")
    public String  showRegOnFaculty(Model model, @PathVariable("id") Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid faculty Id:" + id));


        model.addAttribute("faculty", faculty);
//        model.addAttribute("user", user);
        return "registrationOnFaculty";
    }

    @PostMapping("/register/{id}")
    public String regOnFaculty(@PathVariable("id") Long id,
                                Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUserName(principal.getName());
        University university = universityRepository.findByUniversityName(user.getInstitutionName());

        userService.registerOnFaculty(user, facultyRepository.findFacultyById(id));
        model.addAttribute("successReg", true);

        model.addAttribute("faculties", facultyService.allFacultiesFrom(university));
        model.addAttribute("user", user);
        return "applicantAllFaculties";
    }

    @GetMapping("/applications")
    @ResponseBody
    public ModelAndView listApplications(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUserName(principal.getName());

        modelAndView.addObject("requests", facultyService.applicationsOfUser(user));
        modelAndView.setViewName("applications");
        return modelAndView;
    }


//    @GetMapping("/applicant/allUsers")
//    public String listStudent(Model model) {
//        model.addAttribute("users", userService.allUsers());
//        return "adminAllUsers";
//    }

}
package com.project.SelectionCommittee.controller;

import com.project.SelectionCommittee.model.User;
import com.project.SelectionCommittee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (!bindingResult.hasErrors()) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User Name or Password invalid, please verify");
            modelAndView.addObject("user", new User());

        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }

//    @GetMapping("/admin/home")
//    public String home(){
////        ModelAndView modelAndView = new ModelAndView();
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        User user = userService.findUserByUserName(auth.getName());
////        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
////        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
////        modelAndView.setViewName("admin");
////        return modelAndView;
//
//        return "home.html";
//    }


}

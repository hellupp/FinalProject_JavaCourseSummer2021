package com.project.SelectionCommittee.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/admin").setViewName("adminHome");
        registry.addViewController("/applicant").setViewName("applicantHome");
        registry.addViewController("/allUsers").setViewName("adminAllUsers");
        registry.addViewController("/allFaculties").setViewName("adminAllFaculties");
        registry.addViewController("/add_faculty").setViewName("addFaculty");
        registry.addViewController("/update").setViewName("editFaculty");
        registry.addViewController("/showFaculties").setViewName("applicantAllFaculties");
        registry.addViewController("/register").setViewName("registrationOnFaculty");
        registry.addViewController("/applications").setViewName("applications");
    }

}

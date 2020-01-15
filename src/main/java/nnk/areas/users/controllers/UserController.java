package nnk.areas.users.controllers;

import nnk.areas.users.entities.User;
import nnk.areas.users.services.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("users")
public class UserController {

    private final SecurityUserDetailsService userDetailsService;

    @Autowired
    public UserController(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("view", "user/profile");
        model.addAttribute("title", "Profile");

        return "base-layout";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        Set<User> patients = this.userDetailsService.getPatients();

        model.addAttribute("view", "doctor/patients");
        model.addAttribute("title", "Profile");
        model.addAttribute("patients", patients);

        return "base-layout";
    }

    @GetMapping("/patients/{id}")
    public String patientDetail(@PathVariable("id") long id,  Model model) {
        User patient = this.userDetailsService.getUser(id);

        model.addAttribute("view", "doctor/patient");
        model.addAttribute("title", patient.getFullName());
        model.addAttribute("patient", patient);

        return "base-layout";
    }

    //TODO Add patients
}

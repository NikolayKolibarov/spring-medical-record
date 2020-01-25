package nnk.areas.users.controllers;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.services.ExaminationService;
import nnk.areas.users.models.binding.user.PatientAddModel;
import nnk.areas.users.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

import nnk.areas.users.entities.User;
import nnk.areas.users.services.SecurityUserDetailsService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("users")
public class UserController {

    private final SecurityUserDetailsService userDetailsService;
    private final DoctorService doctorService;
    private final ExaminationService examinationService;

    @Autowired
    public UserController(SecurityUserDetailsService userDetailsService, DoctorService doctorService, ExaminationService examinationService) {
        this.userDetailsService = userDetailsService;
        this.examinationService = examinationService;
        this.doctorService = doctorService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("view", "user/profile");
        model.addAttribute("title", "Profile");
        model.addAttribute("user", this.userDetailsService.getAuthenticatedUser());

        return "base-layout";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        List<User> patients = this.doctorService.getPatients();

        model.addAttribute("view", "doctor/patients");
        model.addAttribute("title", "Profile");
        model.addAttribute("patients", patients);

        return "base-layout";
    }

    @GetMapping("/patients/add")
    public String patientCreate(Model model) {

        model.addAttribute("view", "doctor/patient-add");
        model.addAttribute("title", "Add Patient");

        if (!model.containsAttribute("patientAddModel")) {
            model.addAttribute("patientAddModel", new PatientAddModel());
        }

        return "base-layout";
    }

    @PostMapping("/patients/add")
    public String store(@Valid PatientAddModel patientAddModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("patientAddModel", patientAddModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.patientAddModel", bindingResult);
            return "redirect:/users/patients/create";
        }

        this.doctorService.patientAdd(patientAddModel);

        return "redirect:/users/patients";
    }

    @GetMapping("/patients/{id}")
    public String patientDetail(@PathVariable("id") long id, Model model) {
        User patient = this.userDetailsService.getUser(id);
        List<Examination> examinations = this.examinationService.getPatientExaminations(id);

        model.addAttribute("view", "doctor/patient");
        model.addAttribute("title", patient.getFullName());
        model.addAttribute("patient", patient);
        model.addAttribute("examinations", examinations);

        return "base-layout";
    }

}

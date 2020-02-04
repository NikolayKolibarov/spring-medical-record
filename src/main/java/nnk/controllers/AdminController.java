package nnk.controllers;

import nnk.areas.examinations.services.ExaminationService;
import nnk.areas.treatments.services.TreatmentService;
import nnk.areas.users.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nnk.areas.users.services.SecurityUserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("admin")
public class AdminController {
    private final SecurityUserDetailsService userDetailsService;
    private final ExaminationService examinationService;
    private final TreatmentService treatmentService;
    private final DoctorService doctorService;

    @Autowired
    public AdminController(SecurityUserDetailsService userDetailsService, ExaminationService examinationService, TreatmentService treatmentService, DoctorService doctorService) {
        this.userDetailsService = userDetailsService;
        this.examinationService = examinationService;
        this.treatmentService = treatmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/dashboard")
    public String profile(Model model) {
        model.addAttribute("view", "admin/dashboard");
        model.addAttribute("title", "Profile");
        model.addAttribute("totalDoctors", 10);
        model.addAttribute("totalPatients", 10);
        model.addAttribute("totalExaminations", this.examinationService.getAllExaminations().size());

        return "admin/admin-layout";
    }


}

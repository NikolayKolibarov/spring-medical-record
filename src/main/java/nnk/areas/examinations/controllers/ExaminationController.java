package nnk.areas.examinations.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("examinations")
public class ExaminationController {

    @GetMapping(value = "/patients/{patientId}/examinations/{examinationId}")
    public String getAllExaminations(@PathVariable Long patientId, @PathVariable Long examinationId, Model model) {
        model.addAttribute("view", "examinations/profile");
        model.addAttribute("title", "Profile");

        return "base-layout";
    }
}

package nnk.areas.examinations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nnk.areas.examinations.models.binding.examination.ExaminationCreateModel;
import nnk.areas.examinations.services.DiagnosisService;
import nnk.areas.examinations.services.ExaminationService;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAuthenticated()")
//@RequestMapping("examinations")
public class ExaminationController {
    private final DiagnosisService diagnosisService;
    private final ExaminationService examinationService;

    @Autowired
    public ExaminationController(DiagnosisService diagnosisService, ExaminationService examinationService) {
        this.diagnosisService = diagnosisService;
        this.examinationService = examinationService;
    }



    @GetMapping("/users/patients/{patientId}/examinations/create")
    public String create(@PathVariable("patientId") long patientId,  Model model) {
        model.addAttribute("view", "doctor/examination/create");
        model.addAttribute("title", "Create Examination");
        model.addAttribute("patientId", patientId);
        model.addAttribute("diagnoses", this.diagnosisService.findAll());

        if(!model.containsAttribute("examinationCreateModel")){
            model.addAttribute("examinationCreateModel", new ExaminationCreateModel());
        }

        return "base-layout";
    }

    @PostMapping("/users/patients/{patientId}/examinations/create")
    public String store(@PathVariable("patientId") long patientId, @Valid ExaminationCreateModel examinationCreateModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,  Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("examinationCreateModel", examinationCreateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.examinationCreateModel", bindingResult);
            return "redirect:/users/patients/{patientId}/examinations/create";
        }

        this.examinationService.create(examinationCreateModel);

        return "redirect:/users/patients/{patientId}";
    }

    @GetMapping("/users/patients/{patientId}/examinations/{examinationId}")
    public String getPatientExaminations(@PathVariable Long patientId, @PathVariable Long examinationId, Model model) {
        model.addAttribute("view", "doctor/examination/examination");
        model.addAttribute("title", "Examination");
        model.addAttribute("patientId", patientId);
        model.addAttribute("examination", this.examinationService.findById(examinationId));

        return "base-layout";
    }

    @GetMapping("/users/profile/examinations/{examinationId}")
    public String getUserExaminations(@PathVariable Long examinationId, Model model) {
        model.addAttribute("view", "user/examination");
        model.addAttribute("title", "Examination");
        model.addAttribute("examination", this.examinationService.findById(examinationId));

        return "base-layout";
    }




}

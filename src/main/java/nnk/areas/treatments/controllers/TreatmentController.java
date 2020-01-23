package nnk.areas.treatments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nnk.areas.treatments.models.binding.treatment.TreatmentCreateModel;
import nnk.areas.treatments.services.DrugService;
import nnk.areas.treatments.services.TreatmentService;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAuthenticated()")
//@RequestMapping("treatments")
public class TreatmentController {
    private final DrugService drugService;
    private final TreatmentService treatmentService;

    @Autowired
    public TreatmentController(DrugService drugService, TreatmentService treatmentService) {
        this.drugService = drugService;
        this.treatmentService = treatmentService;
    }



    @GetMapping("/users/patients/{patientId}/examinations/{examinationId}/treatments/create")
    public String create(@PathVariable("patientId") long patientId, @PathVariable("examinationId") long examinationId,  Model model) {
        model.addAttribute("view", "doctor/treatment/create");
        model.addAttribute("title", "Create Treatment");
        model.addAttribute("patientId", patientId);
        model.addAttribute("examinationId", examinationId);
        model.addAttribute("drugs", this.drugService.findAll());

        if(!model.containsAttribute("treatmentCreateModel")){
            model.addAttribute("treatmentCreateModel", new TreatmentCreateModel());
        }

        return "base-layout";
    }

    @PostMapping("/users/patients/{patientId}/examinations/{examinationId}/treatments/create")
    public String store(@PathVariable("patientId") long patientId, @Valid TreatmentCreateModel treatmentCreateModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,  Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("treatmentCreateModel", treatmentCreateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.treatmentCreateModel", bindingResult);
            return "redirect:/users/patients/{patientId}/treatments/create";
        }

        this.treatmentService.create(treatmentCreateModel);

        return "redirect:/users/patients/{patientId}/examinations/{examinationId}";
    }

    @GetMapping("/users/patients/{patientId}/examinations/{examinationId}/treatments/{treatmentId}")
    public String getPatientTreatments(@PathVariable Long patientId,  @PathVariable Long examinationId, @PathVariable Long treatmentId, Model model) {
        model.addAttribute("view", "doctor/treatment/treatment");
        model.addAttribute("title", "Treatment");
        model.addAttribute("patientId", patientId);
        model.addAttribute("treatment", this.treatmentService.findById(treatmentId));

        return "base-layout";
    }

    @GetMapping("/users/profile/examinations/{examinationId}/treatments/{treatmentId}")
    public String getUserTreatments(@PathVariable Long examinationId, @PathVariable Long treatmentId, Model model) {
        model.addAttribute("view", "user/treatment");
        model.addAttribute("title", "Treatment");
        model.addAttribute("treatment", this.treatmentService.findById(treatmentId));

        return "base-layout";
    }

}

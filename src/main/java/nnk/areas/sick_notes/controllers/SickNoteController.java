package nnk.areas.sick_notes.controllers;

import nnk.areas.sick_notes.models.binding.sick_note.SickNoteCreateModel;
import nnk.areas.sick_notes.services.SickNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAuthenticated()")
//@RequestMapping("treatments")
public class SickNoteController {
    private final SickNoteService sickNoteService;

    @Autowired
    public SickNoteController(SickNoteService sickNoteService) {
        this.sickNoteService = sickNoteService;
    }

    @GetMapping("/users/patients/{patientId}/examinations/{examinationId}/sick-notes/create")
    public String create(@PathVariable("patientId") long patientId, @PathVariable("examinationId") long examinationId,  Model model) {
        model.addAttribute("view", "doctor/sick-note/create");
        model.addAttribute("title", "Create Sick Note");
        model.addAttribute("patientId", patientId);
        model.addAttribute("examinationId", examinationId);

        if(!model.containsAttribute("sickNoteCreateModel")){
            model.addAttribute("sickNoteCreateModel", new SickNoteCreateModel());
        }

        return "base-layout";
    }

    @PostMapping("/users/patients/{patientId}/examinations/{examinationId}/sick-notes/create")
    public String store(@PathVariable("patientId") long patientId, @Valid SickNoteCreateModel sickNoteCreateModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("sickNoteCreateModel", sickNoteCreateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sickNoteCreateModel", bindingResult);
            return "redirect:/users/patients/{patientId}/sick-notes/create";
        }

        this.sickNoteService.create(sickNoteCreateModel);

        return "redirect:/users/patients/{patientId}/examinations/{examinationId}";
    }

    @GetMapping("/users/patients/{patientId}/examinations/{examinationId}/sick-notes/{sickNoteId}")
    public String getPatientSickNotes(@PathVariable Long patientId,  @PathVariable Long examinationId, @PathVariable Long sickNoteId, Model model) {
        model.addAttribute("view", "doctor/sick-note/sick-note");
        model.addAttribute("title", "Sick Note");
        model.addAttribute("patientId", patientId);
        model.addAttribute("sickNote", this.sickNoteService.findById(sickNoteId));

        return "base-layout";
    }

    @GetMapping("/users/profile/examinations/{examinationId}/sick-notes/{sickNoteId}")
    public String getUserSickNotes(@PathVariable Long examinationId, @PathVariable Long sickNoteId, Model model) {
        model.addAttribute("view", "user/sick-note");
        model.addAttribute("title", "Sick Note");
        model.addAttribute("sickNote", this.sickNoteService.findById(sickNoteId));

        return "base-layout";
    }

}

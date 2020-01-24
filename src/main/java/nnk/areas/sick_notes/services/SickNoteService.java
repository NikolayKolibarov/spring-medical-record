package nnk.areas.sick_notes.services;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.repositories.ExaminationRepository;
import nnk.areas.sick_notes.entities.SickNote;
import nnk.areas.sick_notes.models.binding.sick_note.SickNoteCreateModel;
import nnk.areas.sick_notes.repositories.SickNoteRepository;
import nnk.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SickNoteService {
    private final UserRepository userRepository;
    private final SickNoteRepository sickNoteRepository;
    private final ExaminationRepository examinationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SickNoteService(UserRepository userRepository, SickNoteRepository sickNoteRepository, ExaminationRepository examinationRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.sickNoteRepository = sickNoteRepository;
        this.examinationRepository = examinationRepository;
        this.modelMapper = modelMapper;
    }

    public SickNote findById(Long id) {
        return this.sickNoteRepository.findById(id);
    }

    public void create(SickNoteCreateModel sickNoteCreateModel) {
        Examination examination = this.examinationRepository.findById(sickNoteCreateModel.getExamination());

        SickNote sickNote = new SickNote();
        sickNote.setExamination(examination);

        this.modelMapper.map(sickNoteCreateModel, sickNote);
        this.sickNoteRepository.save(sickNote);
    }

}

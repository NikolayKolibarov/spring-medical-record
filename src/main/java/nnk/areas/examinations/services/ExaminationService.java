package nnk.areas.examinations.services;

import nnk.areas.examinations.entities.Diagnosis;
import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.models.binding.examination.ExaminationCreateModel;
import nnk.areas.examinations.repositories.DiagnosisRepository;
import nnk.areas.examinations.repositories.ExaminationRepository;
import nnk.areas.users.entities.User;
import nnk.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationService {
    private final DiagnosisRepository diagnosisRepository;
    private final ExaminationRepository examinationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ExaminationService(DiagnosisRepository diagnosisRepository, ExaminationRepository examinationRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.examinationRepository = examinationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Examination findById(Long id) {
        return this.examinationRepository.findById(id);
    }

    public void create(ExaminationCreateModel examinationCreateModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User doctor = userRepository.findByEmail(username);
        User patient = userRepository.findById(examinationCreateModel.getPatient());
        Diagnosis diagnosis = diagnosisRepository.findOne(examinationCreateModel.getDiagnosis());

        Examination examination = new Examination();
        examination.setDoctor(doctor);
        examination.setPatient(patient);

        System.out.println(examination.getDiagnoses());

        examination.getDiagnoses().add(diagnosis);
        diagnosis.getExaminations().add(examination);

        this.modelMapper.map(examinationCreateModel, examination);
        this.examinationRepository.save(examination);

    }

    public List<Examination> getPatientExaminations(Long patientId) {
        return this.examinationRepository.findAllByPatientId(patientId);
    }

}

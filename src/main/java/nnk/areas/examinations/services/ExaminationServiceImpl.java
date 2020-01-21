package nnk.areas.examinations.services;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.models.binding.examination.ExaminationCreateModel;
import nnk.areas.examinations.repositories.DiagnosisRepository;
import nnk.areas.examinations.repositories.ExaminationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    private final DiagnosisRepository diagnosisRepository;
    private final ExaminationRepository examinationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ExaminationServiceImpl(DiagnosisRepository diagnosisRepository, ExaminationRepository examinationRepository, ModelMapper modelMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.examinationRepository = examinationRepository;
        this.modelMapper = modelMapper;
    }

    public Examination findById(Long id) {
        return this.examinationRepository.findById(id);
    }

    @Override
    public void create(ExaminationCreateModel examinationCreateModel) {
        Examination examination = new Examination();

        this.modelMapper.map(examinationCreateModel, examination);
        this.examinationRepository.save(examination);

        //TODO Add Diagnosis
        //examination.addDiagnosis(this.diagnosisRepository.findOne(examinationCreateModel.getDiagnosis()));

    }

}

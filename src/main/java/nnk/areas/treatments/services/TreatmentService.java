package nnk.areas.treatments.services;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.repositories.ExaminationRepository;
import nnk.areas.treatments.models.binding.treatment.TreatmentCreateModel;
import nnk.areas.treatments.entities.Drug;
import nnk.areas.treatments.entities.Treatment;
import nnk.areas.treatments.repositories.DrugRepository;
import nnk.areas.treatments.repositories.TreatmentRepository;
import nnk.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {
    private final DrugRepository drugRepository;
    private final TreatmentRepository treatmentRepository;
    private final UserRepository userRepository;
    private final ExaminationRepository examinationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public TreatmentService(DrugRepository drugRepository, TreatmentRepository treatmentRepository, UserRepository userRepository, ExaminationRepository examinationRepository, ModelMapper modelMapper) {
        this.drugRepository = drugRepository;
        this.treatmentRepository = treatmentRepository;
        this.userRepository = userRepository;
        this.examinationRepository = examinationRepository;
        this.modelMapper = modelMapper;
    }

    public Treatment findById(Long id) {
        return this.treatmentRepository.findById(id);
    }

    public void create(TreatmentCreateModel treatmentCreateModel) {
        Drug drug = this.drugRepository.findOne(treatmentCreateModel.getDrug());
        Examination examination = this.examinationRepository.findById(treatmentCreateModel.getExamination());

        Treatment treatment = new Treatment();
        treatment.setExamination(examination);
        treatment.getDrugs().add(drug);
        drug.getTreatments().add(treatment);

        this.modelMapper.map(treatmentCreateModel, treatment);
        this.treatmentRepository.save(treatment);

    }

}

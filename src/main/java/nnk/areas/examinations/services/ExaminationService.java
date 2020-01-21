package nnk.areas.examinations.services;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.examinations.models.binding.examination.ExaminationCreateModel;

public interface ExaminationService {
    Examination findById(Long id);

    void create(ExaminationCreateModel examinationCreateModel);

}

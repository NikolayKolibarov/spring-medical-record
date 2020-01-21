package nnk.areas.examinations.services;
import nnk.areas.examinations.entities.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    Diagnosis findByName(String name);

    List<Diagnosis> findAll();
}

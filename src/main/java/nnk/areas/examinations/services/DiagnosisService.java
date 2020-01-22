package nnk.areas.examinations.services;

import nnk.areas.examinations.entities.Diagnosis;
import nnk.areas.examinations.repositories.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis findByName(String name) {
        return this.diagnosisRepository.findByName(name);
    }

    public List<Diagnosis> findAll() {
        return this.diagnosisRepository.findAll();
    }

}

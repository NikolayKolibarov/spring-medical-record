package nnk.areas.treatments.services;

import nnk.areas.treatments.entities.Drug;
import nnk.areas.treatments.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Drug findByName(String name) {
        return this.drugRepository.findByName(name);
    }

    public List<Drug> findAll() {
        return this.drugRepository.findAll();
    }

}

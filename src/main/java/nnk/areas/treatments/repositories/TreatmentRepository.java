package nnk.areas.treatments.repositories;

import nnk.areas.examinations.entities.Examination;
import nnk.areas.treatments.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    Treatment findById(Long id);

}

package nnk.areas.examinations.repositories;

import nnk.areas.examinations.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    Examination findById(Long id);

    List<Examination> findAllByPatientId(Long id);
}

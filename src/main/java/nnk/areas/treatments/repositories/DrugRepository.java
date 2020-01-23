package nnk.areas.treatments.repositories;

import nnk.areas.treatments.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Drug findByName(String name);
}

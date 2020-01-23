package nnk.areas.sick_notes.repositories;

import nnk.areas.sick_notes.entities.SickNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SickNoteRepository extends JpaRepository<SickNote, Long> {
    SickNote findById(Long id);

}

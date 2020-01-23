package nnk.areas.treatments.entities;

import nnk.areas.treatments.entities.Drug;
import nnk.areas.examinations.entities.Examination;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relations
    @ManyToOne
    @JoinColumn(name = "examination_id", nullable = false)
    private Examination examination;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "treatment_drug",
            joinColumns = @JoinColumn(name = "treatment_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private Set<Drug> drugs = new HashSet<>();

    // Fields
    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
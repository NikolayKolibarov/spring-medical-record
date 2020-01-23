package nnk.areas.examinations.entities;

import nnk.areas.treatments.entities.Treatment;
import nnk.areas.users.entities.Role;
import nnk.areas.users.entities.User;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "examinations")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relations
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @OneToMany(mappedBy = "examination")
    private Set<Treatment> treatments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "examination_diagnosis",
            joinColumns = @JoinColumn(name = "examination_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
    private Set<Diagnosis> diagnoses = new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name = "type_id", nullable = false)
//    private ExaminationType type;

    // Fields
    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "ambulatory_number")
    private Integer ambulatoryNumber;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "objective_condition")
    private String objectiveCondition;

    @Column(name = "additional_diagnosis")
    private String additionalDiagnosis;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Diagnosis> getDiagnoses() {
        return this.diagnoses;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }
}
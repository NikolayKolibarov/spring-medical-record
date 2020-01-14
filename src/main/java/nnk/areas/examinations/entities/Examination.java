package nnk.areas.examinations.entities;

import nnk.areas.users.entities.User;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ExaminationType type;

    // Fields
    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "ambulatory_number")
    private Integer ambulatoryNumber;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "objective_condition")
    private String objectiveCondition;

    @Column(name = "additional_diagnosis")
    private String additionalDiagnosis;

}
package nnk.areas.sick_notes.entities;

import nnk.areas.examinations.entities.Examination;

import javax.persistence.*;

@Entity
@Table(name = "sick_notes")
public class SickNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relations
    @ManyToOne
    @JoinColumn(name = "examination_id", nullable = false)
    private Examination examination;

    // Fields
    @Column(name = "date")
    private String date;

    @Column(name = "days")
    private Integer days;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
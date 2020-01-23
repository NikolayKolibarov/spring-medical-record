package nnk.areas.sick_notes.models.binding.treatment;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class SickNoteCreateModel {

    @NotBlank
    private String date;

    @NotNull
    private Long examination;

    @NotBlank
    private String days;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getExamination() {
        return this.examination;
    }

    public void setExamination(Long examination) {
        this.examination = examination;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}

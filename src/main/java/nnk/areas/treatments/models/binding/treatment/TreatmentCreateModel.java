package nnk.areas.treatments.models.binding.treatment;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class TreatmentCreateModel {

    @NotBlank
    private String date;

    @NotNull
    private Long examination;

    @NotNull
    private Long drug;

    @NotBlank
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDrug() {
        return drug;
    }

    public void setDrug(Long drug) {
        this.drug = drug;
    }
}

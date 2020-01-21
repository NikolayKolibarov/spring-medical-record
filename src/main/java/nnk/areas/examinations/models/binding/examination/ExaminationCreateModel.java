package nnk.areas.examinations.models.binding.examination;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ExaminationCreateModel {

    @NotBlank
    private String date;

    @NotBlank
    private String time;

    @NotNull
    private Integer ambulatoryNumber;

    @NotBlank
    private String anamnesis;

    @NotBlank
    private String objectiveCondition;

    @NotNull
    private Long diagnosis;

    @NotBlank
    private String additionalDiagnosis;

    @NotNull
    private Long patient;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAmbulatoryNumber() {
        return ambulatoryNumber;
    }

    public void setAmbulatoryNumber(Integer ambulatoryNumber) {
        this.ambulatoryNumber = ambulatoryNumber;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {

        this.anamnesis = anamnesis;
    }

    public String getObjectiveCondition() {

        return objectiveCondition;
    }

    public void setObjectiveCondition(String objectiveCondition) {

        this.objectiveCondition = objectiveCondition;
    }

    public Long getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Long diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdditionalDiagnosis() {
        return additionalDiagnosis;
    }

    public void setAdditionalDiagnosis(String additionalDiagnosis) {
        this.additionalDiagnosis = additionalDiagnosis;
    }

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }
}

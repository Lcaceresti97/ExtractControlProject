package com.sti.extractcontrolmodule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * ExtractedLogModel class to represent ExtractedLogDto entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_extracted_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtractedLogModel {

    @Id
    @Column(name = "id_extracted", unique = true, nullable = false, length = 64)
    private String extractedId;

    @Column(name = "name_extracted", nullable = false)
    private String extractName;

    @Column(name = "id_usuario", nullable = false)
    private String userNameId;

    @Column(name = "date_extracted", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateExtract;

    @Column(name = "id_activity", nullable = false)
    private String activityId;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "idx")
    private String index;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus status;

    @Column(name = "type_book", nullable = false)
    private String typeOfBookId;

    @Column(name = "tomo", nullable = false)
    private int tomo;

    @Column(name = "inscription", nullable = false)
    private int inscription;

    @Column(name = "successive_tract", nullable = false)
    private String tractId;

    @Column(name = "id_horizontal", nullable = false)
    private String horizontalId;

    @Column(name = "observation")
    private String observation;

    @Column(name = "incident_report")
    private String incidentReport;

    @Column(name = "status_extracted", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus statusExtractLog;

    public static ExtractedLogModel buildExtractedLogFromDto(ExtractedLogModel extractedLogModel){
        extractedLogModel.setExtractedId(UUID.randomUUID().toString());

        //Add status IN_TRAINING
        extractedLogModel.setStatusExtractLog(ModelStatus.IN_TRAINING);
        extractedLogModel.setStatus(ModelStatus.ACTIVE);
        return extractedLogModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtractedLogModel that = (ExtractedLogModel) o;
        return tomo == that.tomo && inscription == that.inscription && extractedId.equals(that.extractedId) && extractName.equals(that.extractName) && userNameId.equals(that.userNameId) && dateExtract.equals(that.dateExtract) && activityId.equals(that.activityId) && registrationNumber.equals(that.registrationNumber) && index.equals(that.index) && status == that.status && typeOfBookId.equals(that.typeOfBookId) && tractId.equals(that.tractId) && horizontalId.equals(that.horizontalId) && observation.equals(that.observation) && incidentReport.equals(that.incidentReport) && statusExtractLog == that.statusExtractLog;
    }

    @Override
    public int hashCode() {
        return Objects.hash(extractedId, extractName, userNameId, dateExtract, activityId, registrationNumber, index, status, typeOfBookId, tomo, inscription, tractId, horizontalId, observation, incidentReport, statusExtractLog);
    }
}

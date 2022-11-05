package com.sti.extractcontrolmodule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * QualityControllerLogModel class to represent QualityControllerLogDto entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_quality_controller_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QualityControllerLogModel {

    @Id
    @Column(name = "id_quality_controller_log", unique = true, nullable = false, length = 64)
    private String qualityControllerLogId;

    @Column(name = "name_quality_controller_log", nullable = false, length = 255)
    private String qualityControllerLogName;

    @Column(name = "id_usuario", nullable = false)
    private String userNameId;

    @Column(name = "date_quality_controller", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateQualityController;

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

    @Column(name = "status_quality_controller")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus statusQualityControllerLog;

    public static QualityControllerLogModel buildQualityControllerLogFromDto(QualityControllerLogModel qualityControllerLogModel){
        qualityControllerLogModel.setQualityControllerLogId(UUID.randomUUID().toString());
        qualityControllerLogModel.setStatusQualityControllerLog(ModelStatus.ACTIVE);
        qualityControllerLogModel.setStatus(ModelStatus.IN_TRAINING);
        return qualityControllerLogModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityControllerLogModel that = (QualityControllerLogModel) o;
        return tomo == that.tomo && inscription == that.inscription && qualityControllerLogId.equals(that.qualityControllerLogId) && qualityControllerLogName.equals(that.qualityControllerLogName) && userNameId.equals(that.userNameId) && dateQualityController.equals(that.dateQualityController) && activityId.equals(that.activityId) && registrationNumber.equals(that.registrationNumber) && index.equals(that.index) && status == that.status && typeOfBookId.equals(that.typeOfBookId) && tractId.equals(that.tractId) && horizontalId.equals(that.horizontalId) && observation.equals(that.observation) && incidentReport.equals(that.incidentReport) && statusQualityControllerLog == that.statusQualityControllerLog;
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualityControllerLogId, qualityControllerLogName, userNameId, dateQualityController, activityId, registrationNumber, index, status, typeOfBookId, tomo, inscription, tractId, horizontalId, observation, incidentReport, statusQualityControllerLog);
    }
}

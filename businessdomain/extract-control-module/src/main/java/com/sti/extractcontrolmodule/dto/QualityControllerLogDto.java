package com.sti.extractcontrolmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * Quality_Controller_Log DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */

@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a QualityControllerLog data that user receive when make a request method")
public class QualityControllerLogDto {


    @JsonProperty
    @ApiModelProperty(name = "id_quality_controller_log", required = true, example = "37987ff4-0tcb-4f7d-8d32-e19cd1t0c24e", value = "Unique Id of status after it's created")
    private String qualityControllerLogId;

    @JsonProperty()
    @ApiModelProperty(name = "qualityControllerLogName", required = true, example = "Juan Perez", value = "Name of extract")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String qualityControllerLogName;

    @JsonProperty
    @ApiModelProperty(name = "id_user", required = true, example = "Antony19", value = "User name of ExtractLog")
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 50)
    private String userNameId;

    @JsonProperty()
    @ApiModelProperty(name = "date", required = true, example = "2022-03-25", value = "Date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateQualityController;

    @JsonProperty
    @ApiModelProperty(name = "activityId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of activity after it's created")
    private String activityId;

    @JsonProperty
    @ApiModelProperty(name = "registration_number", required = true, example="223560", value = "Unique Id of ExtractLog after it´s created")
    @Size(max = 255)
    @NotNull
    private String registrationNumber;

    @JsonProperty
    @ApiModelProperty(name = "idx", required = true, example="", value = "Index")
    @Size(max = 255)
    private String index;

    @JsonProperty("ccStatus")
    @ApiModelProperty(name = "status", example = "IN_TRAINING", value = "status")
    private ModelStatus status;

    @JsonProperty
    @ApiModelProperty(name = "typeOfBookId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of type of book after it's created")
    @NotNull
    @Size(min = 2, max = 100)
    private String typeOfBookId;

    @JsonProperty
    @ApiModelProperty(name = "tomo", required = true, example = "270", value = "Tomo of MainTable")
    @Positive
    @NotNull
    private int tomo;

    @JsonProperty()
    @ApiModelProperty(name = "inscription", required = true, example = "99", value = "Inscription of MainTable")
    @NotNull
    @Positive
    private int inscription;

    @JsonProperty
    @ApiModelProperty(name = "tractId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of tract after it's created")
    @Size(max = 100, min = 2)
    private String tractId;

    @JsonProperty
    @ApiModelProperty(name = "horizontalId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of horizontal after it's created")
    @Size(max = 100, min = 2)
    private String horizontalId;

    @JsonProperty
    @ApiModelProperty(name = "observation", required = true, example = "Sigue en estado en formación", value = "Observartion of extract log")
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String observation;

    @JsonProperty
    @ApiModelProperty(name = "incident report", required = true, value = "incident report of extract log")
    private String incidentReport;

    @JsonProperty
    @ApiModelProperty(name = "statusQualityControllerLog", example = "ACTIVE", value = "Status_Quality_Controller_Log")
    private ModelStatus statusQualityControllerLog;
}

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
 * MainTable DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */

@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a MainTable data that user receive when make a request method")
public class MainTableDto {

    @JsonProperty
    @ApiModelProperty(name = "id_main", required = true, example="237bbb83-e86d-4fd0-8f80-378264c92ad5", value = "Unique Id of MainTable after it´s created")
    private String idMain;

    @JsonProperty
    @ApiModelProperty(name = "registration_number", required = true, example="223555", value = "Unique Id of MainTable after it´s created")
    @Size(max = 255)
    @NotNull
    private String registrationNumber;

    @JsonProperty
    @ApiModelProperty(name = "idx", required = true, example="", value = "Index")
    @Size(max = 255)
    private String index;

    @JsonProperty
    @ApiModelProperty(name = "typeOfBookId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of type of book after it's created")
    @NotNull
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

    @JsonProperty("activityStatus")
    @ApiModelProperty(name = "mainTableStatus", example = "IN_TRAINING", value = "Status")
    private ModelStatus mainTableStatus;

    @JsonProperty
    @ApiModelProperty(name = "extractUser", required = true, example = "Juan Perez", value = "Extract_User of MainTable")
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 50)
    private String estractUser;

    @JsonProperty
    @ApiModelProperty(name = "creationDate", required = true, example = "2022-02-25", value = "Date Approval ")
    @NotNull
    private Date creationDate;

    @JsonProperty
    @ApiModelProperty(name = "user_quality_control", required = true, example = "Antony Amador", value = "User_Quality_Control of MainTable")
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 50)
    private String userQualityControl;

    @JsonProperty
    @ApiModelProperty(name = "date_Quality_Control", required = true, example = "2022-02-25", value = "Date_Quality_Control ")
    @NotNull
    private Date dateQualityControl;

    @JsonProperty
    @ApiModelProperty(name = "ccStatus", example = "ACTIVE", value = "Status_CC")
    private ModelStatus statusCCId;

    @JsonProperty()
    @ApiModelProperty(name = "deliveryDate", required = true, example = "2022-02-25", value = "Delivery Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
}

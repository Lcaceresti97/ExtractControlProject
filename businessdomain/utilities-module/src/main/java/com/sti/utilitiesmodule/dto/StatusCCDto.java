package com.sti.utilitiesmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.utilitiesmodule.model.status.ModelStatus;
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
 * StatusCC DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a Status CC data that user receive when make a request method")
public class StatusCCDto {

    @JsonProperty
    @ApiModelProperty(name = "statusCCId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of status after it's created")
    private String statusCCId;

    @JsonProperty()
    @ApiModelProperty(name = "statusName", required = true, example = "Activa", value = "Name of status")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String statusName;

    @JsonProperty()
    @ApiModelProperty(name = "statusDescription", required = true, example = "Estado activa de la bitacora", value = "Description of status")
    @NotNull
    @Size(min = 2, max = 100)
    private String statusDescription;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25 16:43:27.485894", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("ccStatus")
    @ApiModelProperty(name = "ccStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus ccStatus;
}

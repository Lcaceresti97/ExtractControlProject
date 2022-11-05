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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Activity DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a Activity data that user receive when make a request method")
public class ActivityDto {

    @JsonProperty
    @ApiModelProperty(name = "activityId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of activity after it's created")
    private String activityId;

    @JsonProperty()
    @ApiModelProperty(name = "activityName", required = true, example = "EXTRACTADO", value = "Name of Activity")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String activityName;

    @JsonProperty()
    @ApiModelProperty(name = "activityDescription", required = true, example = "Estado de la Actividad", value = "Description of Activity")
    @NotNull
    @Size(min = 2, max = 100)
    private String activityDescription;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("activityStatus")
    @ApiModelProperty(name = "activityStatus", example = "ACTIVE", value = "Status")
    private ModelStatus activityStatus;
}

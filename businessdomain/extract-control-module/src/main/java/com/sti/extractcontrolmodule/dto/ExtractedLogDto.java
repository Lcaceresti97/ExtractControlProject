package com.sti.extractcontrolmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * Extract_Log DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */

@JsonSerialize
@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a ExtractLog data that user receive when make a request method")
public class ExtractedLogDto {

    @JsonProperty
    @ApiModelProperty(name = "extractedId", required = true, example = "4623d0fb-17d5-4134-847c-92b128eb99c4", value = "Unique Id of status after it's created")
    private String extractedId;

    @JsonProperty()
    @ApiModelProperty(name = "extractName", required = true, example = "Juan Perez", value = "Name of extract")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String extractName;

    @JsonProperty
    @ApiModelProperty(name = "id_user", required = true, example = "Jua21@", value = "User name of ExtractLog")
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 50)
    private String userNameId;

    @JsonProperty()
    @ApiModelProperty(name = "date", required = false, example = "2022-09-21", value = "Date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateExtract;

    @JsonProperty
    @ApiModelProperty(name = "activityId", required = true, example = "cda2f3d0-7dc5-46df-9789-c77aec858b01", value = "Unique Id of activity after it's created")
    private String activityId;

    @JsonProperty
    @ApiModelProperty(name = "registration_number", required = true, example="22350", value = "Unique Id of ExtractLog after it´s created")
    @Size(max = 255)
    @NotNull
    private String registrationNumber;

    @JsonProperty
    //@Null
    @Size(max = 255)
    private String index;

    @JsonProperty("ccStatus")
    @ApiModelProperty(name = "status", example = "IN_TRAINING", value = "Status")
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
    @ApiModelProperty(name = "horizontalId", required = true, example = "237bbb83-e86d-4fd0-8f80-378264c92ad5", value = "Unique Id of horizontal after it's created")
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
    //@Null
    private String incidentReport;

    @JsonProperty
    @ApiModelProperty(name = "ccStatus", example = "ACTIVE", value = "Status_CC")
    private ModelStatus statusExtractLog;
}

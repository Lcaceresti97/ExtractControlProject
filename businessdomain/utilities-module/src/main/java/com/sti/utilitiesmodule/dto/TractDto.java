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
 * Tract DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a Tract data that user receive when make a request method")
public class TractDto {

    @JsonProperty
    @ApiModelProperty(name = "tractId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of tract after it's created")
    private String tractId;

    @JsonProperty()
    @ApiModelProperty(name = "tractName", required = true, example = "Si", value = "Name of Tract")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String tractName;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25 16:43:27.485894", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("tractStatus")
    @ApiModelProperty(name = "tractStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus tractStatus;
}

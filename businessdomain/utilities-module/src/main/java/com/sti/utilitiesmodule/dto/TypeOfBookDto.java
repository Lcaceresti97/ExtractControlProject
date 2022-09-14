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
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * TypeOfBook DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a TypeOfBook data that user receive when make a request method")
public class TypeOfBookDto {

    @JsonProperty
    @ApiModelProperty(name = "typeOfBookId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of type of book after it's created")
    private String typeOfBookId;

    @JsonProperty()
    @ApiModelProperty(name = "typeOfBookName", required = true, example = "INA", value = "Name of Type Of Book")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String typeOfBookName;

    @JsonProperty()
    @ApiModelProperty(name = "typeOfBookDescription", required = true, example = "INA", value = "Description of Type Of Book")
    @NotNull
    @Size(min = 2, max = 100)
    private String typeOfBookDescription;

    @JsonProperty()
    @ApiModelProperty(name = "typeOfBookTomo", required = true, example = "270", value = "Tomo of Type Of Book")
    @NotNull
    @Positive
    private int typeOfBookTomo;

    @JsonProperty()
    @ApiModelProperty(name = "typeOfBookInscription", required = true, example = "99", value = "Inscription of Type Of Book")
    @NotNull
    @Positive
    private int typeOfBookInscription;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25 16:43:27.485894", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("typeOfBookStatus")
    @ApiModelProperty(name = "typeOfBookStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus typeOfBookStatus;
}

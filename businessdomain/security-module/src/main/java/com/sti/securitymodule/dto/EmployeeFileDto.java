package com.sti.securitymodule.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.securitymodule.model.Job;
import com.sti.securitymodule.model.User;
import com.sti.securitymodule.model.status.ModelStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * EmployeeFile DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a EmployeeFile data that user receive when make a request method")
public class EmployeeFileDto {

    @JsonProperty
    @ApiModelProperty(name = "employeeFileId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of Employee File after it's created")
    private String employeeFileId;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFileName", required = true, example = "Mauricio Santos", value = "Name of Employee File")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String employeeFileName;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFileAge", required = true, example = "45", value = "Age of Employee")
    @NotNull
    @Positive
    private int employeeFileAge;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFileAddress", required = true, example = "Col. El mogote", value = "Address of Employee")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String employeeFileAddress;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFileDni", required = true, example = "0801199706897", value = "DNI of Employee")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String employeeFileDni;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFilePhone", required = true, example = "96857487", value = "Phone of Employee")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String employeeFilePhone;

    @JsonProperty()
    @ApiModelProperty(name = "employeeFileEmail", required = true, example = "mauricioperez@gmail.com", value = "Email of Employee")
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 64)
    @Email(message = "Employee email must be valid")
    private String employeeFileEmail;

    @JsonIgnore
    @JsonProperty("job")
    private Job job;

    @JsonProperty("employeeFileUser")
    private User user;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("employeeFileStatus")
    @ApiModelProperty(name = "employeeFileStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus employeeFileStatus;

}

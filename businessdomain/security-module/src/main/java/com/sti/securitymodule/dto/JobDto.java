package com.sti.securitymodule.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sti.securitymodule.model.EmployeeFile;
import com.sti.securitymodule.model.Job;
import com.sti.securitymodule.model.status.ModelStatus;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Job DTO class to encapsulate implementation of entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model represent a Job data that user receive when make a request method")

public class JobDto {

    @JsonProperty
    @ApiModelProperty(name = "jobId", required = true, example = "37987ff4-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Unique Id of Job after it's created")
    private String jobId;

    @JsonProperty()
    @ApiModelProperty(name = "jobName", required = true, example = "GERENTE", value = "Job of Rol")
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String jobName;

    @JsonProperty()
    @ApiModelProperty(name = "jobDescription", required = true, example = "Encargado de la gerencia de la empresa", value = "Description of Job")
    @NotNull
    @Size(min = 2, max = 100)
    private String jobDescription;

    @JsonProperty("jobStatus")
    @ApiModelProperty(name = "jobStatus", example = "ACTIVE OF INACTIVE", value = "Status")
    private ModelStatus jobStatus;

    @JsonProperty()
    @ApiModelProperty(name = "transactionUser", required = true, example = "78978456-0ecb-4f8d-8d33-e19cd1d0c24e", value = "Transaction User")
    @NotNull
    private String transactionUser;

    @JsonProperty()
    @ApiModelProperty(name = "registrationDate", required = true, example = "2022-02-25", value = "Registration Date ")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonProperty("employeeFiles")
    @Builder.Default
    private List<EmployeeFile> employeeFiles = new ArrayList<>();

    public List<EmployeeFile> getEmployeeFiles() {
        return employeeFiles;
    }

    public void setEmployeeFiles(List<EmployeeFile> employeeFiles) {
        this.employeeFiles = employeeFiles;
    }
}

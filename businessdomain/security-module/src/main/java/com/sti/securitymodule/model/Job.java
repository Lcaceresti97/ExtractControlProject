package com.sti.securitymodule.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sti.securitymodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Job class to represent Job entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {

    @Id
    @Column(name = "job_id", unique = true, nullable = false, length = 64)
    private String jobId;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @Column(name = "job_status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus jobStatus;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @JsonBackReference
    @OneToMany(mappedBy="job", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<EmployeeFile> jobEmployeeFile = new ArrayList<>();

    @JsonIgnore
    public List<EmployeeFile> getJobEmployeeFile() {
        return jobEmployeeFile;
    }

    @JsonIgnore
    public void setJobEmployeeFile(List<EmployeeFile> jobEmployeeFile) {
        this.jobEmployeeFile = jobEmployeeFile;
    }

    /**
     * Adds fields which are not populated by Job DTO.
     *
     * @return
     */
    public static Job buildFromDtoJob(Job job) {
        job.setJobId(UUID.randomUUID().toString());
        job.setJobStatus(ModelStatus.ACTIVE);
        return job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Job job = (Job) o;
        return this.jobId == job.jobId
                && (this.jobName.equals(job.jobName))
                && (this.jobDescription.equals(job.jobDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.jobName == null ? 0 : this.jobName.hashCode());
        hash = 31 * hash + (this.jobId == null ? 0 : this.jobId.hashCode());
        hash = 31 * hash + (this.jobDescription == null ? 0 : this.jobDescription.hashCode());
        return hash;
    }

}

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
 * EmployeeFile class to represent EmployeeFile entity.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_employee_file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeFile {

    @Id
    @Column(name = "employee_file_id", nullable = false, unique = true, length = 64)
    private String employeeFileId;

    @Column(name = "employee_file_name", nullable = false)
    private String employeeFileName;

    @Column(name = "employee_file_age", nullable = false)
    private int employeeFileAge;

    @Column(name = "employee_file_address", nullable = false)
    private String employeeFileAddress;

    @Column(name = "employee_file_dni", nullable = false)
    private String employeeFileDni;

    @Column(name = "employee_file_phone", nullable = false)
    private String employeeFilePhone;

    @Column(name = "employee_file_email", nullable = false)
    private String employeeFileEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "employee_file_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus employeeFileStatus;


    /**
     * Adds fields which are not populated by EmployeeFile DTO.
     *
     * @return
     */
    public static EmployeeFile buildFromDtoEmployeeFile(EmployeeFile employeeFile) {
        employeeFile.setEmployeeFileId(UUID.randomUUID().toString());
        employeeFile.setEmployeeFileStatus(ModelStatus.ACTIVE);
        return employeeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        EmployeeFile employeeFile = (EmployeeFile) o;
        return this.employeeFileId == employeeFile.employeeFileId
                && (this.employeeFileName.equals(employeeFile.employeeFileName))
                && (this.employeeFileAddress.equals(employeeFile.employeeFileAddress));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.employeeFileName == null ? 0 : this.employeeFileName.hashCode());
        hash = 31 * hash + (this.employeeFileId == null ? 0 : this.employeeFileId.hashCode());
        hash = 31 * hash + (this.employeeFileAddress == null ? 0 : this.employeeFileAddress.hashCode());
        return hash;
    }


}

package com.sti.utilitiesmodule.model;

import com.sti.utilitiesmodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Status CC class to represent Status CC entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_status_cc")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusCC {

    @Id
    @Column(name = "status_cc_id", nullable = false, unique = true, length = 64)
    private String statusCCId;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "status_description", nullable = false)
    private String statusDescription;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "cc_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus ccStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by Status DTO.
     * @return
     */
    public static StatusCC buildStatusFromDto(StatusCC statusCC){
        statusCC.setStatusCCId(UUID.randomUUID().toString());
        statusCC.setCcStatus(ModelStatus.ACTIVE);
        return statusCC;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        StatusCC statusCC = (StatusCC) o;
        return this.statusCCId == statusCC.statusCCId
                && (this.statusName.equals(statusCC.statusName))
                && (this.statusDescription.equals(statusCC.statusDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.statusName == null ? 0 : this.statusName.hashCode());
        hash = 31 * hash + (this.statusCCId == null ? 0 : this.statusCCId.hashCode());
        hash = 31 * hash + (this.statusDescription == null ? 0 : this.statusDescription.hashCode());
        return hash;
    }
}

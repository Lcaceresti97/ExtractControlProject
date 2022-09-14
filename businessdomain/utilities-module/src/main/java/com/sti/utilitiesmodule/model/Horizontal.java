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
 * Horizontal class to represent Horizontal entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_horizontal")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Horizontal {

    @Id
    @Column(name = "horizontal_id", nullable = false, unique = true, length = 64)
    private String horizontalId;

    @Column(name = "horizontal_name", nullable = false)
    private String horizontalName;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "horizontal_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus horizontalStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by Horizontal DTO.
     * @return
     */
    public static Horizontal buildHorizontalFromDto(Horizontal horizontal){
        horizontal.setHorizontalId(UUID.randomUUID().toString());
        horizontal.setHorizontalStatus(ModelStatus.ACTIVE);
        return horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Horizontal horizontal = (Horizontal) o;
        return this.horizontalId == horizontal.horizontalId
                && (this.horizontalName.equals(horizontal.horizontalName));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.horizontalName == null ? 0 : this.horizontalName.hashCode());
        hash = 31 * hash + (this.horizontalId == null ? 0 : this.horizontalId.hashCode());
        return hash;
    }
}

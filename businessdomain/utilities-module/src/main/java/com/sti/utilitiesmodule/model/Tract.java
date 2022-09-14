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
 * Tract class to represent Tract entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_tract")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tract {

    @Id
    @Column(name = "tract_id", nullable = false, unique = true, length = 64)
    private String tractId;

    @Column(name = "tract_name", nullable = false)
    private String tractName;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "tract_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus tractStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by Tract DTO.
     * @return
     */
    public static Tract buildTractFromDto(Tract tract){
        tract.setTractId(UUID.randomUUID().toString());
        tract.setTractStatus(ModelStatus.ACTIVE);
        return tract;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Tract tract = (Tract) o;
        return this.tractId == tract.tractId
                && (this.tractName.equals(tract.tractName));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.tractName == null ? 0 : this.tractName.hashCode());
        hash = 31 * hash + (this.tractId == null ? 0 : this.tractId.hashCode());
        return hash;
    }
}

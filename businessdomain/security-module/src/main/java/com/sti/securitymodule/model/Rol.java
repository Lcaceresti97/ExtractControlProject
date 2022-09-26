package com.sti.securitymodule.model;

import com.sti.securitymodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Rol class to represent Rol entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_rol")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rol {

    @Id
    @Column(name = "rol_id", unique = true, nullable = false, length = 64)
    private String rolId;

    @Column(name = "rol_name", nullable = false)
    private String rolName;

    @Column(name = "rol_description", nullable = false)
    private String rolDescription;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "rol_status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus rolStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by Rol DTO.
     * @return
     */
    public static Rol buildFromDtoRol(Rol rol){
        rol.setRolId(UUID.randomUUID().toString());
        rol.setRolStatus(ModelStatus.ACTIVE);
        return rol;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Rol rol = (Rol) o;
        return this.rolId == rol.rolId
                && (this.rolName.equals(rol.rolName))
                && (this.rolDescription.equals(rol.rolDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.rolName == null ? 0 : this.rolName.hashCode());
        hash = 31 * hash + (this.rolId == null ? 0 : this.rolId.hashCode());
        hash = 31 * hash + (this.rolDescription == null ? 0 : this.rolDescription.hashCode());
        return hash;
    }

}

package com.sti.extractcontrolmodule.model;

import com.sti.extractcontrolmodule.model.status.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * MainTable class to represent MainTableDto entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_main")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MainTableModel {

    @Id
    @Column(name = "id_main", unique = true, updatable = false, length = 64)
    private String idMain;

    @Column(name = "registration_name", updatable = false)
    private String registrationNumber;

    @Column(name = "idx")
    private String index;

    @Column(name = "type_book", updatable = false)
    private String typeOfBookId;

    @Column(name = "tomo", nullable = false)
    private int tomo;

    @Column(name = "inscription", nullable = false)
    private int inscription;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus mainTableStatus;

    @Column(name = "extract_user", updatable = false)
    private String estractUser;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "quality_controller_user", nullable = false)
    private String userQualityControl;

    @Column(name = "date_quality_controller", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateQualityControl;

    @Column(name = "status_cc", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus statusCCId;

    @Column(name = "delivary_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    public static MainTableModel buildMainTableFromDto(MainTableModel model){
        model.setIdMain(UUID.randomUUID().toString());

        //Add status IN_TRAINING
        model.setStatusCCId(ModelStatus.ACTIVE);
        return model;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainTableModel that = (MainTableModel) o;
        return tomo == that.tomo && inscription == that.inscription && idMain.equals(that.idMain) && registrationNumber.equals(that.registrationNumber) && index.equals(that.index) && typeOfBookId.equals(that.typeOfBookId) && mainTableStatus == that.mainTableStatus && estractUser.equals(that.estractUser) && creationDate.equals(that.creationDate) && userQualityControl.equals(that.userQualityControl) && dateQualityControl.equals(that.dateQualityControl) && statusCCId == that.statusCCId && deliveryDate.equals(that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMain, registrationNumber, index, typeOfBookId, tomo, inscription, mainTableStatus, estractUser, creationDate, userQualityControl, dateQualityControl, statusCCId, deliveryDate);
    }
}

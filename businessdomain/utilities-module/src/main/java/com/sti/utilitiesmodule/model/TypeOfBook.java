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
 * TypeOfBook class to represent TypeOfBook entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_type_of_book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeOfBook {

    @Id
    @Column(name = "type_of_book_id", nullable = false, unique = true, length = 64)
    private String typeOfBookId;
    @Column(name = "type_of_book_name", nullable = false)
    private String typeOfBookName;
    @Column(name = "type_of_book_description", nullable = false)
    private String typeOfBookDescription;

    @Column(name = "type_of_book_tomo", nullable = false)
    private int typeOfBookTomo;

    @Column(name = "type_of_book_inscription", nullable = false)
    private int typeOfBookInscription;

    @Column(name = "transaction_user", nullable = false)
    private String transactionUser;

    @Column(name = "type_of_book_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus typeOfBookStatus;

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    /**
     * Adds fields which are not populated by TypeOfBook DTO.
     * @return
     */
    public static TypeOfBook buildTypeOfBookFromDto(TypeOfBook typeOfBook){
        typeOfBook.setTypeOfBookId(UUID.randomUUID().toString());
        typeOfBook.setTypeOfBookStatus(ModelStatus.ACTIVE);
        return typeOfBook;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        TypeOfBook typeOfBook = (TypeOfBook) o;
        return this.typeOfBookId == typeOfBook.typeOfBookId
                && (this.typeOfBookName.equals(typeOfBook.typeOfBookName))
                && (this.typeOfBookDescription.equals(typeOfBook.typeOfBookDescription));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.typeOfBookName == null ? 0 : this.typeOfBookName.hashCode());
        hash = 31 * hash + (this.typeOfBookId == null ? 0 : this.typeOfBookId.hashCode());
        hash = 31 * hash + (this.typeOfBookDescription == null ? 0 : this.typeOfBookDescription.hashCode());
        return hash;
    }
}

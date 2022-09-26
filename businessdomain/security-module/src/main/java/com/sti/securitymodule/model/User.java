package com.sti.securitymodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sti.securitymodule.model.status.ModelStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Activity class to represent User entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 64)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private EmployeeFile employeeFile;

     @Column(name = "user_status")
     @Enumerated(EnumType.ORDINAL)
     private ModelStatus userStatus;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_rols", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> userRols = new ArrayList<>();



    /**
     * Adds fields which are not populated by User DTO.
     * @return
     */
    public static User buildFromDtoUser(User user){
        user.setId(UUID.randomUUID().toString());
        user.setUserStatus(ModelStatus.ACTIVE);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        User user = (User) o;
        return this.id == user.id
                && (this.name.equals(user.name))
                && (this.username.equals(user.username));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.name == null ? 0 : this.name.hashCode());
        hash = 31 * hash + (this.id == null ? 0 : this.id.hashCode());
        hash = 31 * hash + (this.username == null ? 0 : this.username.hashCode());
        return hash;
    }
}

package com.sti.securitymodule.repository;

import com.sti.securitymodule.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Rol entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

    /**
     * Find Rol by its name.
     * @param rolName String
     * @return Optional Rol
     */
    Optional<Rol> findByRolName(String rolName);
}

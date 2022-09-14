package com.sti.utilitiesmodule.repository;

import com.sti.utilitiesmodule.model.Tract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Tract entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface TractRepository extends JpaRepository<Tract, String> {
}

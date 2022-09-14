package com.sti.utilitiesmodule.repository;

import com.sti.utilitiesmodule.model.StatusCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for StatusCC entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface StatusCCRepository extends JpaRepository<StatusCC, String> {
}

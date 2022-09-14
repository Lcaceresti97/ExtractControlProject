package com.sti.utilitiesmodule.repository;

import com.sti.utilitiesmodule.model.Horizontal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Horizontal entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface HorizontalRepository extends JpaRepository<Horizontal, String> {
}

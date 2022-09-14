package com.sti.utilitiesmodule.repository;

import com.sti.utilitiesmodule.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Activity entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
}

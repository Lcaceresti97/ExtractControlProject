package com.sti.extractcontrolmodule.repository;

import com.sti.extractcontrolmodule.model.MainTableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for MainTableModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface MainTableRepository extends JpaRepository<MainTableModel, String> {

    boolean existsByRegistrationNumber(String registrationNumber);
}

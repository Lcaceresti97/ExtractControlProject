package com.sti.securitymodule.repository;

import com.sti.securitymodule.model.EmployeeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for EmployeeFile entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, String> {
}

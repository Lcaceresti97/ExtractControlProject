package com.sti.securitymodule.repository;

import com.sti.securitymodule.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Job entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface JobRepository extends JpaRepository<Job, String> {


}

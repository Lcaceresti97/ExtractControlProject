package com.sti.extractcontrolmodule.repository;

import com.sti.extractcontrolmodule.model.ExtractedLogModel;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository for ExtractedLogModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface ExtraxtedLogRepository extends JpaRepository<ExtractedLogModel,String> {

    ExtractedLogModel findByRegistrationNumber(String registrationNumber);

    ExtractedLogModel findByUserNameId(String userNameId);

    List<ExtractedLogModel> findByStatusExtractLogAndDateExtractAndUserNameId(ModelStatus modelStatus, Date dateExtract, String userNameId);

    List<ExtractedLogModel> findByStatusExtractLog(ModelStatus modelStatus);

    boolean existsByRegistrationNumber(String registrationNumber);
}

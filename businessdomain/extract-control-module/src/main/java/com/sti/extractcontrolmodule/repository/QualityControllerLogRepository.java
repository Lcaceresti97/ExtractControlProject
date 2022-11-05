package com.sti.extractcontrolmodule.repository;

import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import com.sti.extractcontrolmodule.model.QualityControllerLogModel;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository for QualityControllerLogModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Repository
public interface QualityControllerLogRepository extends JpaRepository<QualityControllerLogModel,String> {

    List<QualityControllerLogModel> findByStatusQualityControllerLogAndDateQualityControllerAndUserNameId(ModelStatus modelStatus, Date dateQualityController, String userNameId);
    boolean existsByRegistrationNumber(String registrationNumber);
}

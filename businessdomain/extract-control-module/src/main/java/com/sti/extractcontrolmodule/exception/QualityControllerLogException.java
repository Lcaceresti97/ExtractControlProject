package com.sti.extractcontrolmodule.exception;

import com.sti.extractcontrolmodule.model.QualityControllerLogModel;

public class QualityControllerLogException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildQualityControllerLogNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(QualityControllerLogModel.class, field, fieldValue);
    }

    /**
     * @param qualityControllerLogtId
     * @return
     */
    public static ResourceNotFoundException
    buildQualityControllerLogNotFoundExceptionForId(String qualityControllerLogtId) {
        return resourceNotFoundExceptionOf(QualityControllerLogModel.class, "qualityControllerLogtId", qualityControllerLogtId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildQualityControllerLogNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(QualityControllerLogModel.class, searchParams);
    }

}

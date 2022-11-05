package com.sti.extractcontrolmodule.exception;

import com.sti.extractcontrolmodule.model.ExtractedLogModel;

public class ExtractedLogNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildExtractedLogNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(ExtractedLogModel.class, field, fieldValue);
    }

    /**
     * @param extractedId
     * @return
     */
    public static ResourceNotFoundException
    buildExtractedLogNotFoundExceptionForId(String extractedId) {
        return resourceNotFoundExceptionOf(ExtractedLogModel.class, "extractedId", extractedId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildExtractedLogNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(ExtractedLogModel.class, searchParams);
    }

}

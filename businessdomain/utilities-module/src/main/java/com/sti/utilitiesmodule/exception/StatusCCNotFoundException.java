package com.sti.utilitiesmodule.exception;

import com.sti.utilitiesmodule.model.StatusCC;

/**
 * StatusCC 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class StatusCCNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildStatusCCNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(StatusCC.class, field, fieldValue);
    }

    /**
     * @param statusCCId
     * @return
     */
    public static ResourceNotFoundException
    buildStatusCCNotFoundExceptionForId(String statusCCId) {
        return resourceNotFoundExceptionOf(StatusCC.class, "statusCCId", statusCCId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildStatusCCNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(StatusCC.class, searchParams);
    }

}

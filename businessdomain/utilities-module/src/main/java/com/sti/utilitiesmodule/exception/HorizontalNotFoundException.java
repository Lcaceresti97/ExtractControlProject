package com.sti.utilitiesmodule.exception;

import com.sti.utilitiesmodule.model.Horizontal;

/**
 * Horizontal 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class HorizontalNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildHorizontalNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(Horizontal.class, field, fieldValue);
    }

    /**
     * @param horizontalId
     * @return
     */
    public static ResourceNotFoundException
    buildHorizontalNotFoundExceptionForId(String horizontalId) {
        return resourceNotFoundExceptionOf(Horizontal.class, "horizontalId", horizontalId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildHorizontalNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(Horizontal.class, searchParams);
    }

}

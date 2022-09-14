package com.sti.utilitiesmodule.exception;

import com.sti.utilitiesmodule.model.Tract;

/**
 * Tract 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class TractNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildTractNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(Tract.class, field, fieldValue);
    }

    /**
     * @param tractId
     * @return
     */
    public static ResourceNotFoundException
    buildTractNotFoundExceptionForId(String tractId) {
        return resourceNotFoundExceptionOf(Tract.class, "tractId", tractId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildTractNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(Tract.class, searchParams);
    }
}

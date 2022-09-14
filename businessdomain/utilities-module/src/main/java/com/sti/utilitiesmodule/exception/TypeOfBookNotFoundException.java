package com.sti.utilitiesmodule.exception;

import com.sti.utilitiesmodule.model.TypeOfBook;

/**
 * Tract 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class TypeOfBookNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildTypeOfBookNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(TypeOfBook.class, field, fieldValue);
    }

    /**
     * @param typeOfBookId
     * @return
     */
    public static ResourceNotFoundException
    buildTypeOfBookNotFoundExceptionForId(String typeOfBookId) {
        return resourceNotFoundExceptionOf(TypeOfBook.class, "typeOfBookId", typeOfBookId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildTypeOfBookNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(TypeOfBook.class, searchParams);
    }
}

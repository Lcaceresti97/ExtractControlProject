package com.sti.extractcontrolmodule.exception;


import com.sti.extractcontrolmodule.model.MainTableModel;

/**
 * MainTable 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class MainTableNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildMainTableNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(MainTableModel.class, field, fieldValue);
    }

    /**
     * @param mainId
     * @return
     */
    public static ResourceNotFoundException
    buildMainTableNotFoundExceptionForId(String mainId) {
        return resourceNotFoundExceptionOf(MainTableModel.class, "mainId", mainId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildMainTableNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(MainTableModel.class, searchParams);
    }

}

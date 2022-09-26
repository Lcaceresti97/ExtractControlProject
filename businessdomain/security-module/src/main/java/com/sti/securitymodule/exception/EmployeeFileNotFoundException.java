package com.sti.securitymodule.exception;

import com.sti.securitymodule.model.EmployeeFile;

/**
 * Job 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class EmployeeFileNotFoundException extends ResourceNotFoundException{

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildEmployeeFileNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(EmployeeFile.class, field, fieldValue);
    }

    /**
     *
     * @param employeeFileId Long
     * @return
     */
    public static ResourceNotFoundException
    buildEmployeeFileNotFoundExceptionForId(String employeeFileId){
        return resourceNotFoundExceptionOf(EmployeeFile.class, "employeeFileId", employeeFileId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildEmployeeFileNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(EmployeeFile.class, searchParams);
    }
}

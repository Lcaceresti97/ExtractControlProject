package com.sti.securitymodule.exception;

import com.sti.securitymodule.model.Rol;

/**
 * Rol 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class RolNotFoundException extends ResourceNotFoundException{

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildRolNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Rol.class, field, fieldValue);
    }

    /**
     *
     * @param rolId Long
     * @return
     */
    public static ResourceNotFoundException
    buildRolNotFoundExceptionForId(String rolId){
        return resourceNotFoundExceptionOf(Rol.class, "rolId", rolId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildRolNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Rol.class, searchParams);
    }

}

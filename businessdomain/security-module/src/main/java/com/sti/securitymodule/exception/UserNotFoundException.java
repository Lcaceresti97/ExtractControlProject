package com.sti.securitymodule.exception;

import com.sti.securitymodule.model.User;

/**
 * User 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class UserNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildUserNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(User.class, field, fieldValue);
    }

    /**
     * @param id
     * @return
     */
    public static ResourceNotFoundException
    buildUserNotFoundExceptionForId(String id) {
        return resourceNotFoundExceptionOf(User.class, "id", id);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildUserNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(User.class, searchParams);
    }

}

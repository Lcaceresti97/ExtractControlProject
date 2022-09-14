package com.sti.utilitiesmodule.exception;

import com.sti.utilitiesmodule.model.Activity;

/**
 * Activity 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class ActivityNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildActivityNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(Activity.class, field, fieldValue);
    }

    /**
     * @param activityId
     * @return
     */
    public static ResourceNotFoundException
    buildActivityNotFoundExceptionForId(String activityId) {
        return resourceNotFoundExceptionOf(Activity.class, "activityId", activityId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildActivityNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(Activity.class, searchParams);
    }
}

package com.sti.securitymodule.exception;

import com.sti.securitymodule.model.Job;

/**
 * Job 404 status exception.
 *
 * @author Laurent G. Cáceres
 */
public class JobNotFoundException extends ResourceNotFoundException{

    /**
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildJobNotFoundExceptionForField(String field, String fieldValue) {
        return resourceNotFoundExceptionOf(Job.class, field, fieldValue);
    }

    /**
     * @param jobId
     * @return
     */
    public static ResourceNotFoundException
    buildJobNotFoundExceptionForId(String jobId) {
        return resourceNotFoundExceptionOf(Job.class, "jobId", jobId);
    }

    /**
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildJobNotFoundException(String... searchParams) {
        return resourceNotFoundExceptionOf(Job.class, searchParams);
    }
}

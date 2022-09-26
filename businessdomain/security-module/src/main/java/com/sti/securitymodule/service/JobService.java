package com.sti.securitymodule.service;

import com.sti.securitymodule.dto.JobDto;
import com.sti.securitymodule.exception.JobNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for Job entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface JobService {

    /**
     * Saves given Job into DB.
     * @param jobDto Job
     */
    JobDto saveJob(JobDto jobDto);

    /**
     * Find Job by its ID.
     * @param jobId String
     * @return Job jobDto
     * @throws JobNotFoundException when no Job is found by ID
     */
    JobDto findJobById(final String jobId) throws JobNotFoundException;

    /**
     * Return a page of sorted Job.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Job.
     */
    Page<JobDto> findPaginatedSortedJob(int page, int size, String[] sort);


    /**
     * Update given Job into DB.
     * @param jobDto Job
     */
    JobDto updateJob(JobDto jobDto);

    /**
     * Delete Job by its ID.
     * @param jobId
     */
    void deleteJob(final String jobId);

}

package com.sti.utilitiesmodule.service;

import com.sti.utilitiesmodule.dto.ActivityDto;
import com.sti.utilitiesmodule.exception.ActivityNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for Activity entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface ActivityService {

    /**
     * Saves given Activity into DB.
     * @param activityDto Activity
     */
    ActivityDto saveActivity(ActivityDto activityDto);

    /**
     * Find Activity by its ID.
     * @param activityId String
     * @return Activity activityDto
     * @throws ActivityNotFoundException when no Activity is found by ID
     */
    ActivityDto findActivityById(final String activityId) throws ActivityNotFoundException;

    /**
     * Return a page of sorted Activity.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Activity.
     */
    Page<ActivityDto> findPaginatedSortedActivity(int page, int size, String[] sort);


    /**
     * Update given Activity into DB.
     * @param activityDto Activity
     */
    ActivityDto updateActivity(ActivityDto activityDto);

    /**
     * Delete Activity by its ID.
     * @param activityId
     */
    void deleteActivity(final String activityId);

}

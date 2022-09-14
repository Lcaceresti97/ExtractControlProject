package com.sti.utilitiesmodule.service;

import com.sti.utilitiesmodule.dto.StatusCCDto;
import com.sti.utilitiesmodule.exception.StatusCCNotFoundException;
import org.springframework.data.domain.Page;

/**
 * StatusCC interface for Activity entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface StatusCCService {

    /**
     * Saves given StatusCC into DB.
     * @param statusCCDto StatusCC
     */
    StatusCCDto saveStatusCC(StatusCCDto statusCCDto);

    /**
     * Find a StatusCC by its ID.
     * @param statusCCId String
     * @return StatusCC statusCCDto
     * @throws StatusCCNotFoundException when no StatusCC is found by ID
     */
    StatusCCDto findMStatusCCById(final String statusCCId) throws StatusCCNotFoundException;

    /**
     * Return a page of sorted StatusCC.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto StatusCC.
     */
    Page<StatusCCDto> findPaginatedSortedStatusCC(int page, int size, String[] sort);

    /**
     * Update given StatusCC into DB.
     * @param statusCCDto StatusCC
     */
    StatusCCDto updateStatusCC(StatusCCDto statusCCDto);

    /**
     * Delete StatusCC by its ID.
     * @param statusCCId
     */
    void deleteStatusCC(final String statusCCId);
}

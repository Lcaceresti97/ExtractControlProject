package com.sti.utilitiesmodule.service;

import com.sti.utilitiesmodule.dto.HorizontalDto;
import com.sti.utilitiesmodule.exception.HorizontalNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Horizontal interface for Activity entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface HorizontalService {

    /**
     * Saves given Horizontal into DB.
     * @param horizontalDto Horizontal
     */
    HorizontalDto saveHorizontal(HorizontalDto horizontalDto);

    /**
     * Find a Horizontal by its ID.
     * @param horizontalId String
     * @return Horizontal horizontalDto
     * @throws HorizontalNotFoundException when no Horizontal is found by ID
     */
    HorizontalDto findHorizontalById(final String horizontalId) throws HorizontalNotFoundException;

    /**
     * Return a page of sorted Horizontals.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Horizontal.
     */
    Page<HorizontalDto> findPaginatedSortedHorizontal(int page, int size, String[] sort);

    /**
     * Update given Horizontal into DB.
     * @param horizontalDto Horizontal
     */
    HorizontalDto updateHorizontal(HorizontalDto horizontalDto);

    /**
     * Delete Horizontal by its ID.
     * @param horizontalId
     */
    void deleteHorizontal(final String horizontalId);
}

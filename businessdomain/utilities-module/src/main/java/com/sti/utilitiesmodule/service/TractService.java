package com.sti.utilitiesmodule.service;

import com.sti.utilitiesmodule.dto.TractDto;
import com.sti.utilitiesmodule.exception.TractNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Tract interface for Activity entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface TractService {

    /**
     * Saves given Tract into DB.
     * @param tractDto Tract
     */
    TractDto saveTract(TractDto tractDto);

    /**
     * Find a Tract by its ID.
     * @param tractId String
     * @return Tract tractDto
     * @throws TractNotFoundException when no Tract is found by ID
     */
    TractDto findTractById(final String tractId) throws TractNotFoundException;

    /**
     * Return a page of sorted Tract.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Tract.
     */
    Page<TractDto> findPaginatedSortedTract(int page, int size, String[] sort);

    /**
     * Update given Tract into DB.
     * @param tractDto Tract
     */
    TractDto updateTract(TractDto tractDto);

    /**
     * Delete Tract by its ID.
     * @param tractId
     */
    void deleteTract(final String tractId);
}

package com.sti.extractcontrolmodule.service;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.exception.ExtractedLogNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Interface for ExtractedLog entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */

public interface ExtractedLogService {
    /**
     * Saves given ExtractedLog into DB.
     * @param extractedLogDto
     */
    ExtractedLogDto saveExtractedLog(ExtractedLogDto extractedLogDto);

    /**
     * Find a ExtractedLog by its ID.
     * @param extractedId String
     * @return ExtractedLog
     * @throws ExtractedLogNotFoundException when no Extracted Log is found by ID
     */
    ExtractedLogDto findExtractedLogById(final String extractedId) throws ExtractedLogNotFoundException;

    /**
     * Return a page of sorted Page<ExtractedLog>.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto ExtractedLog.
     */
    Page<ExtractedLogDto> findPaginatedSortedExtractedLog(int page, int size, String[] sort);

    /**
     * Update given ExtractedLog into DB.
     * @param extractedLogDto
     */
    ExtractedLogDto updateExtractedLog(ExtractedLogDto extractedLogDto);

    /**
     * Delete ExtractedLog by its ID.
     * @param extractedId
     */
    void deleteExtractedLog(final String extractedId);

    /**
     * If exists attribute in Model
     * @param attribute
     * @return boolean
     */
    boolean existsAttribute(String attribute);

    /**
     *
     * @param extractedId
     * @return ExtractedLogDto
     */
    ExtractedLogDto activateExtractedLogRegistrationNumber(final String extractedId);

    /**
     *
     * @param userNameId
     * @return
     */
    List<ExtractedLogDto> findExtractedLogByStatusAndDateExtractAndUserNameId(final String userNameId,final Date fecha);
}
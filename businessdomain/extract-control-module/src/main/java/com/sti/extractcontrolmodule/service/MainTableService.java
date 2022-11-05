package com.sti.extractcontrolmodule.service;

import com.sti.extractcontrolmodule.dto.MainTableDto;
import com.sti.extractcontrolmodule.exception.MainTableNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Interface for MainTable entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface MainTableService {

    /**
     * Saves given MainTable into DB.
     * @param mainTableDto MainTable
     */
    MainTableDto saveMainTable(MainTableDto mainTableDto);

    /**
     * Find a MainTable by its ID.
     * @param mainId String
     * @return MainTableDto mainTableDto
     * @throws MainTableNotFoundException when no MainTable is found by ID
     */
    MainTableDto findMainTableById(final String mainId) throws MainTableNotFoundException;

    /**
     * Return a page of sorted Page<MainTableDto>.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto MainTable.
     */
    Page<MainTableDto> findPaginatedSortedMainTable(int page, int size, String[] sort);

    /**
     * Update given MainTable into DB.
     * @param mainTableDto TypeOfBook
     */
    MainTableDto updateMainTable(MainTableDto mainTableDto);

    /**
     * Delete MainTable by its ID.
     * @param mainId
     */
    void deleteMainTable(final String mainId);

    /**
     *
     * @param mainId String
     * @return MainTableDto
     */
    MainTableDto activateMainTableRegistrationNumber(final String mainId);

    /**
     * If exists attribute in Model
     * @param attribute
     * @return boolean
     */
    boolean existsAttribute(String attribute);
}

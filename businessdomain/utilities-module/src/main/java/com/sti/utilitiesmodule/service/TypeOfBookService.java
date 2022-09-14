package com.sti.utilitiesmodule.service;

import com.sti.utilitiesmodule.dto.TypeOfBookDto;
import com.sti.utilitiesmodule.exception.TypeOfBookNotFoundException;
import org.springframework.data.domain.Page;

/**
 * TypeOfBook interface for Activity entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface TypeOfBookService {

    /**
     * Saves given TypeOfBook into DB.
     * @param typeOfBookDto TypeOfBook
     */
    TypeOfBookDto saveTypeOfBook(TypeOfBookDto typeOfBookDto);

    /**
     * Find a TypeOfBook by its ID.
     * @param typeOfBookId String
     * @return TypeOfBook typeOfBookDto
     * @throws TypeOfBookNotFoundException when no TypeOfBook is found by ID
     */
    TypeOfBookDto findTypeOfBookById(final String typeOfBookId) throws TypeOfBookNotFoundException;

    /**
     * Return a page of sorted TypeOfBook.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto TypeOfBook.
     */
    Page<TypeOfBookDto> findPaginatedSortedTypeOfBook(int page, int size, String[] sort);

    /**
     * Update given TypeOfBook into DB.
     * @param typeOfBookDto TypeOfBook
     */
    TypeOfBookDto updateTypeOfBook(TypeOfBookDto typeOfBookDto);

    /**
     * Delete TypeOfBook by its ID.
     * @param typeOfBookId
     */
    void deleteTypeOfBook(final String typeOfBookId);
}

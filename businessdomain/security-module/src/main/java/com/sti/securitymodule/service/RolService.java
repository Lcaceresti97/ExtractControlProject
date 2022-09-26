package com.sti.securitymodule.service;

import com.sti.securitymodule.dto.RolDto;
import com.sti.securitymodule.exception.RolNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for Rol entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
public interface RolService {

    /**
     * Saves given Rol into DB.
     * @param rolDto Rol
     */
    RolDto saveRol(RolDto rolDto);

    /**
     * Find Rol by its ID.
     * @param rolId String
     * @return Rol rolDto
     * @throws RolNotFoundException when no Rol is found by ID
     */
    RolDto findRolById(final String rolId) throws RolNotFoundException;

    /**
     * Return a page of sorted Rol.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Rol.
     */
    Page<RolDto> findPaginatedSortedRol(int page, int size, String[] sort);


    /**
     * Update given Rol into DB.
     * @param rolDto Rol
     */
    RolDto updateRol(RolDto rolDto);

    /**
     * Delete Rol by its ID.
     * @param rolId
     */
    void deleteRol(final String rolId);
}

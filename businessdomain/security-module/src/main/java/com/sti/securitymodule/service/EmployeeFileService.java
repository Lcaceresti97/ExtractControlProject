package com.sti.securitymodule.service;

import com.sti.securitymodule.dto.EmployeeFileDto;
import com.sti.securitymodule.exception.EmployeeFileNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for Employee File entity crud operations.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */

public interface EmployeeFileService {

    /**
     * Saves given EmployeeFile into DB.
     * @param employeeFileDto EmployeeFile
     */
    EmployeeFileDto saveEmployeeFile(EmployeeFileDto employeeFileDto);

    /**
     * Find EmployeeFile by its ID.
     * @param employeeFileId String
     * @return EmployeeFile employeeFileDto
     * @throws EmployeeFileNotFoundException when no EmployeeFile is found by ID
     */
    EmployeeFileDto findEmployeeFileById(final String employeeFileId) throws EmployeeFileNotFoundException;

    /**
     * Return a page of sorted EmployeeFile.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto EmployeeFile.
     */
    Page<EmployeeFileDto> findPaginatedSortedEmployeeFile(int page, int size, String[] sort);


    /**
     * Update given EmployeeFile into DB.
     * @param employeeFileDto EmployeeFile
     */
    EmployeeFileDto updateEmployeeFile(EmployeeFileDto employeeFileDto);

    /**
     * Delete EmployeeFile by its ID.
     * @param employeeFileId
     */
    void deleteEmployeeFile(final String employeeFileId);

}

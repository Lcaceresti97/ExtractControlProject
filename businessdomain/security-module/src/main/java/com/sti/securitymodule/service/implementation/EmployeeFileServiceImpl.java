package com.sti.securitymodule.service.implementation;

import com.sti.securitymodule.dto.EmployeeFileDto;
import com.sti.securitymodule.exception.EmployeeFileNotFoundException;
import com.sti.securitymodule.model.EmployeeFile;
import com.sti.securitymodule.model.mappers.EmployeeFileMapper;
import com.sti.securitymodule.model.status.ModelStatus;
import com.sti.securitymodule.repository.EmployeeFileRepository;
import com.sti.securitymodule.service.EmployeeFileService;
import com.sti.securitymodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for EmployeeFile entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class EmployeeFileServiceImpl implements EmployeeFileService {

    private final EmployeeFileRepository employeeFileRepository;

    private final EmployeeFileMapper employeeFileMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public EmployeeFileDto saveEmployeeFile(EmployeeFileDto employeeFileDto) {
        EmployeeFile employeeFile = EmployeeFile.buildFromDtoEmployeeFile(this.employeeFileMapper.dtoToEmployeeFile(employeeFileDto));
        this.employeeFileRepository.save(employeeFile);
        return employeeFileMapper.employeeFileToDto(employeeFile);
    }

    @Override
    public EmployeeFileDto findEmployeeFileById(String employeeFileId) throws EmployeeFileNotFoundException {
        EmployeeFile employeeFile = employeeFileRepository.findById(employeeFileId).orElseThrow(() -> EmployeeFileNotFoundException.buildEmployeeFileNotFoundExceptionForId(employeeFileId));
        return employeeFileMapper.employeeFileToDto(isActiveEmployeeFile(employeeFile,"employeeFileId", employeeFileId));
    }

    @Override
    public Page<EmployeeFileDto> findPaginatedSortedEmployeeFile(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<EmployeeFileDto> employeeFileDtos;
        employeeFileDtos = employeeFileMapper
                .employeeFileToDto(employeeFileRepository.findAll(pageable).toList());

        return new PageImpl<>(employeeFileDtos);
    }

    @Override
    public EmployeeFileDto updateEmployeeFile(EmployeeFileDto employeeFileDto) {
        EmployeeFile employeeFile = this.employeeFileMapper.dtoToEmployeeFile(employeeFileDto);
        this.employeeFileRepository.save(employeeFile);
        return employeeFileMapper.employeeFileToDto(employeeFile);
    }

    @Override
    public void deleteEmployeeFile(String employeeFileId) {
        EmployeeFile employeeFile = employeeFileMapper.dtoToEmployeeFile(findEmployeeFileById(employeeFileId));
        employeeFile.setEmployeeFileStatus(ModelStatus.INACTIVE);
        employeeFileRepository.save(employeeFile);
    }

    /**
     * Return EmployeeFile if status code is ACTIVE.
     * @param employeeFile EmployeeFile
     * @param queryField String
     * @param queryFieldValue String
     * @return EmployeeFile
     * @throws EmployeeFileNotFoundException ex
     */
    private EmployeeFile isActiveEmployeeFile(EmployeeFile employeeFile, String queryField, String queryFieldValue){
        if(employeeFile.getEmployeeFileStatus().getStatusCode() == 0){
            return employeeFile;
        }
        throw EmployeeFileNotFoundException
                .buildEmployeeFileNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

package com.sti.securitymodule.model.mappers;

import com.sti.securitymodule.dto.EmployeeFileDto;
import com.sti.securitymodule.model.EmployeeFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeFileMapper {

    EmployeeFile dtoToEmployeeFile(EmployeeFileDto dto);

    EmployeeFileDto employeeFileToDto(EmployeeFile employeeFile);

    List<EmployeeFile> dtoToEmployeeFile(List<EmployeeFileDto> dtos);

    List<EmployeeFileDto> employeeFileToDto(List<EmployeeFile> employeeFiles);
}

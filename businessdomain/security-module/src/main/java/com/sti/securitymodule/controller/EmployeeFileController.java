package com.sti.securitymodule.controller;

import com.sti.securitymodule.dto.EmployeeFileDto;
import com.sti.securitymodule.dto.RolDto;
import com.sti.securitymodule.dto.pageable.PageResponse;
import com.sti.securitymodule.dto.pageable.PageResponseDto;
import com.sti.securitymodule.response.BaseResponse;
import com.sti.securitymodule.response.Response;
import com.sti.securitymodule.service.EmployeeFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for EmployeeFile entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Employee File APIs")
@RestController
@RequestMapping(path = "/api/v1/security/employeeFiles")
@RequiredArgsConstructor
public class EmployeeFileController {

    private final EmployeeFileService employeeFileService;

    /**
     * Handler method for saving Validated given EmployeeFile.
     * @return ResponseEntity Response EmployeeFile
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<EmployeeFileDto>> saveEmployeeFile(@RequestBody @Valid EmployeeFileDto employeeFileDto) {

        EmployeeFileDto savedEmployeeFile = employeeFileService.saveEmployeeFile(employeeFileDto);
        BaseResponse<EmployeeFileDto> employeeFileDtoBaseResponse = new BaseResponse<>();
        return employeeFileDtoBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "EmployeeFile saved successfully", savedEmployeeFile);
    }

    /**
     * Handler method for fetching a single EmployeeFile by its ID.
     * @param employeeFileId String
     * @return ResponseEntity EmployeeFile
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{employeeFileId}")
    public ResponseEntity<? extends EmployeeFileDto> findByEmployeeFileId(@PathVariable final String employeeFileId) {
        EmployeeFileDto retrievedEmployeeFile= employeeFileService.findEmployeeFileById(employeeFileId);
        return new ResponseEntity<>(retrievedEmployeeFile, HttpStatus.OK);
    }


    /**
     * Get Paginated sorted EmployeeFile with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse EmployeeFileDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<EmployeeFileDto>> getEmployeeFiles(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "employeeFileId, desc") String[] sort){

        Page<EmployeeFileDto> employeeFileDtoPage = employeeFileService
                .findPaginatedSortedEmployeeFile(page, size, sort);


        PageResponseDto<EmployeeFileDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(employeeFileDtoPage.getSize(), employeeFileDtoPage.getNumberOfElements(),
                employeeFileDtoPage.getTotalPages(), employeeFileDtoPage.getNumber(), employeeFileDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given Rol.
     * @return ResponseEntity Response Rol
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<EmployeeFileDto>> updateEmployeeFile(@RequestBody @Valid EmployeeFileDto employeeFileDto) {
        EmployeeFileDto updateEmployeeFile= employeeFileService.updateEmployeeFile(employeeFileDto);
        BaseResponse<EmployeeFileDto> employeeFileDtoBaseResponse = new BaseResponse<>();
        return employeeFileDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Employee File Update Successfully", updateEmployeeFile);
    }

    /**
     * Handler method for deleting a Employee File by its ID.
     * @param employeeFileId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{employeeFileId}")
    public ResponseEntity<? extends Response<String>> deleteEmployeeFile(@PathVariable final String employeeFileId) {
        employeeFileService.deleteEmployeeFile(employeeFileId);
        BaseResponse<String> employeeFileIdResponse = new BaseResponse<>();
        return employeeFileIdResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Employee File with ID: ")
                        .append(employeeFileId)
                        .append(" was deleted.").toString(), employeeFileId);
    }
}

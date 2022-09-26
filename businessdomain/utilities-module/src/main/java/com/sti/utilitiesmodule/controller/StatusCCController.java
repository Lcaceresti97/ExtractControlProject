package com.sti.utilitiesmodule.controller;

import com.sti.utilitiesmodule.dto.StatusCCDto;
import com.sti.utilitiesmodule.dto.pageable.PageResponse;
import com.sti.utilitiesmodule.dto.pageable.PageResponseDto;
import com.sti.utilitiesmodule.response.BaseResponse;
import com.sti.utilitiesmodule.response.Response;
import com.sti.utilitiesmodule.service.StatusCCService;
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
 * Controller for StatusCC entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "StatusCC APIs")
@RestController
@RequestMapping(path = "/api/v1/utilities/status")
@RequiredArgsConstructor
public class StatusCCController {

    private final StatusCCService statusCCService;

    /**
     * Handler method for saving Validated given StatusCC.
     *
     * @return ResponseEntity Response StatusCC
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<StatusCCDto>> saveStatusCC(@RequestBody @Valid StatusCCDto statusCCDto) {

        StatusCCDto savedStatusCC = statusCCService.saveStatusCC(statusCCDto);
        BaseResponse<StatusCCDto> statusCCBaseResponse = new BaseResponse<>();
        return statusCCBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "StatusCC saved successfully", savedStatusCC);
    }

    /**
     * Handler method for fetching a single StatusCC by its ID.
     * @param statusCCId String
     * @return ResponseEntity StatusCC
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{statusCCId}")
    public ResponseEntity<? extends StatusCCDto> findByStatusCCId(@PathVariable final String statusCCId) {
        StatusCCDto retrievedStatusCC = statusCCService.findMStatusCCById(statusCCId);
        return new ResponseEntity<>(retrievedStatusCC, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted StatusCC with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse StatusCCDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<StatusCCDto>> getStatusCC(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "statusCCId, desc") String[] sort){

        Page<StatusCCDto> statusCCDtoPage = statusCCService
                .findPaginatedSortedStatusCC(page, size, sort);


        PageResponseDto<StatusCCDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(statusCCDtoPage.getSize(), statusCCDtoPage.getNumberOfElements(),
                statusCCDtoPage.getTotalPages(), statusCCDtoPage.getNumber(), statusCCDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given StatusCC.
     * @return ResponseEntity Response StatusCC
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<StatusCCDto>> updateStatusCC(@RequestBody @Valid StatusCCDto statusCCDto) {
        StatusCCDto updateStatusCC = statusCCService.updateStatusCC(statusCCDto);
        BaseResponse<StatusCCDto> statusCCDtoBaseResponse = new BaseResponse<>();
        return statusCCDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "StatusCC Update Successfully", updateStatusCC);
    }

    /**
     * Handler method for deleting a StatusCC by its ID.
     * @param statusCCId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{statusCCId}")
    public ResponseEntity<? extends Response<String>> deleteStatusCC(@PathVariable final String statusCCId) {
        statusCCService.deleteStatusCC(statusCCId);
        BaseResponse<String> statusCCResponse = new BaseResponse<>();
        return statusCCResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("StatusCC with ID: ")
                        .append(statusCCId)
                        .append(" was deleted.").toString(), statusCCId);
    }

}

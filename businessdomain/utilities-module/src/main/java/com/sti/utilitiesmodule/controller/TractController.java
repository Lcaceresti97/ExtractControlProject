package com.sti.utilitiesmodule.controller;

import com.sti.utilitiesmodule.dto.TractDto;
import com.sti.utilitiesmodule.dto.pageable.PageResponse;
import com.sti.utilitiesmodule.dto.pageable.PageResponseDto;
import com.sti.utilitiesmodule.response.BaseResponse;
import com.sti.utilitiesmodule.response.Response;
import com.sti.utilitiesmodule.service.TractService;
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
 * Controller for Tract entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Tract APIs")
@RestController
@RequestMapping(path = "/api/v1/utilities/tracts")
@RequiredArgsConstructor
public class TractController {

    private final TractService tractService;

    /**
     * Handler method for saving Validated given Tract.
     * @return ResponseEntity Response Tract
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<TractDto>> saveTracts(@RequestBody @Valid TractDto tractDto) {

        TractDto savedTract = tractService.saveTract(tractDto);
        BaseResponse<TractDto> tractBaseResponse = new BaseResponse<>();
        return tractBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Tract saved successfully", savedTract);
    }

    /**
     * Handler method for fetching a single Tract by its ID.
     *
     * @param tractId String
     * @return ResponseEntity Tract
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{tractId}")
    public ResponseEntity<? extends TractDto> findByTractId(@PathVariable final String tractId) {
        TractDto retrievedTract = tractService.findTractById(tractId);
        return new ResponseEntity<>(retrievedTract, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted Tracts with given criteria.
     *
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse TractDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<TractDto>> getTracts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "tractId, desc") String[] sort) {

        Page<TractDto> tractDtoPage = tractService
                .findPaginatedSortedTract(page, size, sort);

        PageResponseDto<TractDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(tractDtoPage.getSize(), tractDtoPage.getNumberOfElements(),
                tractDtoPage.getTotalPages(), tractDtoPage.getNumber(), tractDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given Tract.
     *
     * @return ResponseEntity Response Tract
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<TractDto>> updateTract(@RequestBody @Valid TractDto tractDto) {
        TractDto updateTract = tractService.updateTract(tractDto);
        BaseResponse<TractDto> tractDtoBaseResponse = new BaseResponse<>();
        return tractDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Tract Update Successfully", updateTract);
    }

    /**
     * Handler method for deleting a Tract by its ID.
     *
     * @param tractId String
     * @return Response null
     */
    @DeleteMapping(value = "/{tractId}")
    public ResponseEntity<? extends Response<String>> deleteTract(@PathVariable final String tractId) {
        tractService.deleteTract(tractId);
        BaseResponse<String> TractResponse = new BaseResponse<>();
        return TractResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Tract with ID: ")
                        .append(tractId)
                        .append(" was deleted.").toString(), tractId);
    }
}

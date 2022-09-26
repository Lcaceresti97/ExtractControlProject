package com.sti.utilitiesmodule.controller;

import com.sti.utilitiesmodule.dto.HorizontalDto;
import com.sti.utilitiesmodule.dto.pageable.PageResponse;
import com.sti.utilitiesmodule.dto.pageable.PageResponseDto;
import com.sti.utilitiesmodule.response.BaseResponse;
import com.sti.utilitiesmodule.response.Response;
import com.sti.utilitiesmodule.service.HorizontalService;
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
 * Controller for Horizontal entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Horizontal APIs")
@RestController
@RequestMapping(path = "/api/v1/utilities/horizontal")
@RequiredArgsConstructor
public class HorizontalController {

    private final HorizontalService horizontalService;

    /**
     * Handler method for saving Validated given Horizontal.
     *
     * @return ResponseEntity Response Horizontal
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<HorizontalDto>> saveHorizontal(@RequestBody @Valid HorizontalDto horizontalDto) {

        HorizontalDto savedHorizontal = horizontalService.saveHorizontal(horizontalDto);
        BaseResponse<HorizontalDto> horizontalBaseResponse = new BaseResponse<>();
        return horizontalBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Horizontal saved successfully", savedHorizontal);
    }

    /**
     * Handler method for fetching a single Horizontal by its ID.
     *
     * @param horizontalId String
     * @return ResponseEntity Horizontal
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{horizontalId}")
    public ResponseEntity<? extends HorizontalDto> findHorizontalById(@PathVariable final String horizontalId) {
        HorizontalDto retrievedHorizontal = horizontalService.findHorizontalById(horizontalId);
        return new ResponseEntity<>(retrievedHorizontal, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted Horizontal with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse HorizontalDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<HorizontalDto>> getHorizontal(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "horizontalId, desc") String[] sort){

        Page<HorizontalDto> horizontalDtoPage = horizontalService
                .findPaginatedSortedHorizontal(page, size, sort);


        PageResponseDto<HorizontalDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(horizontalDtoPage.getSize(), horizontalDtoPage.getNumberOfElements(),
                horizontalDtoPage.getTotalPages(), horizontalDtoPage.getNumber(), horizontalDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given Horizontal.
     * @return ResponseEntity Response Horizontal
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<HorizontalDto>> updateHorizontal(@RequestBody @Valid HorizontalDto horizontalDto) {
        HorizontalDto updateHorizontal = horizontalService.updateHorizontal(horizontalDto);
        BaseResponse<HorizontalDto> horizontalDtoBaseResponse = new BaseResponse<>();
        return horizontalDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Horizontal Update Successfully", updateHorizontal);
    }

    /**
     * Handler method for deleting a Horizontal by its ID.
     * @param horizontalId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{horizontalId}")
    public ResponseEntity<? extends Response<String>> deleteHorizontal(@PathVariable final String horizontalId) {
        horizontalService.deleteHorizontal(horizontalId);
        BaseResponse<String> horizontalResponse = new BaseResponse<>();
        return horizontalResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Horizontal with ID: ")
                        .append(horizontalId)
                        .append(" was deleted.").toString(), horizontalId);
    }

}

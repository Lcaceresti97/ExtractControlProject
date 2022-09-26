package com.sti.utilitiesmodule.controller;

import com.sti.utilitiesmodule.dto.TypeOfBookDto;
import com.sti.utilitiesmodule.dto.pageable.PageResponse;
import com.sti.utilitiesmodule.dto.pageable.PageResponseDto;
import com.sti.utilitiesmodule.response.BaseResponse;
import com.sti.utilitiesmodule.response.Response;
import com.sti.utilitiesmodule.service.TypeOfBookService;
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
 * Controller for TypeOfBook entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "TypeOfBook APIs")
@RestController
@RequestMapping(path = "/api/v1/utilities/typeOfBook")
@RequiredArgsConstructor
public class TypeOfBookController {

    private final TypeOfBookService typeOfBookService;

    /**
     * Handler method for saving Validated given TypeOfBook.
     *
     * @return ResponseEntity Response TypeOfBook
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<TypeOfBookDto>> saveTypeOfBook(@RequestBody @Valid TypeOfBookDto typeOfBookDto) {

        TypeOfBookDto savedTypeOfBook = typeOfBookService.saveTypeOfBook(typeOfBookDto);
        BaseResponse<TypeOfBookDto> typeOfBookBaseResponse = new BaseResponse<>();
        return typeOfBookBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "TypeOfBook saved successfully", savedTypeOfBook);
    }

    /**
     * Handler method for fetching a single TypeOfBook by its ID.
     *
     * @param typeOfBookId String
     * @return ResponseEntity TypeOfBook
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{typeOfBookId}")
    public ResponseEntity<? extends TypeOfBookDto> findByTypeOfBookId(@PathVariable final String typeOfBookId) {
        TypeOfBookDto retrievedTypeOfBook = typeOfBookService.findTypeOfBookById(typeOfBookId);
        return new ResponseEntity<>(retrievedTypeOfBook, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted TypeOfBook with given criteria.
     *
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse TypeOfBookDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<TypeOfBookDto>> getTypeOfBook(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "typeOfBookId, desc") String[] sort) {

        Page<TypeOfBookDto> typeOfBookDtoPage = typeOfBookService
                .findPaginatedSortedTypeOfBook(page, size, sort);


        PageResponseDto<TypeOfBookDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(typeOfBookDtoPage.getSize(), typeOfBookDtoPage.getNumberOfElements(),
                typeOfBookDtoPage.getTotalPages(), typeOfBookDtoPage.getNumber(), typeOfBookDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given TypeOfBook.
     *
     * @return ResponseEntity Response TypeOfBook
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<TypeOfBookDto>> updateTypeOfBook(@RequestBody @Valid TypeOfBookDto typeOfBookDto) {
        TypeOfBookDto updateTypeOfBook = typeOfBookService.updateTypeOfBook(typeOfBookDto);
        BaseResponse<TypeOfBookDto> typeOfBookDtoBaseResponse = new BaseResponse<>();
        return typeOfBookDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "TypeOfBook Update Successfully", updateTypeOfBook);
    }

    /**
     * Handler method for deleting a TypeOfBook by its ID.
     *
     * @param typeOfBookId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{typeOfBookId}")
    public ResponseEntity<? extends Response<String>> deleteTypeOfBook(@PathVariable final String typeOfBookId) {
        typeOfBookService.deleteTypeOfBook(typeOfBookId);
        BaseResponse<String> typeOfBookResponse = new BaseResponse<>();
        return typeOfBookResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("TypeOfBook with ID: ")
                        .append(typeOfBookId)
                        .append(" was deleted.").toString(), typeOfBookId);
    }

}

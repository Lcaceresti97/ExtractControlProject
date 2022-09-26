package com.sti.securitymodule.controller;

import com.sti.securitymodule.dto.RolDto;
import com.sti.securitymodule.dto.pageable.PageResponse;
import com.sti.securitymodule.dto.pageable.PageResponseDto;
import com.sti.securitymodule.response.BaseResponse;
import com.sti.securitymodule.response.Response;
import com.sti.securitymodule.service.RolService;
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
 * Controller for Rol entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Rol APIs")
@RestController
@RequestMapping(path = "/api/v1/security/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    /**
     * Handler method for saving Validated given Rol.
     * @return ResponseEntity Response Rol
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<RolDto>> saveRol(@RequestBody @Valid RolDto rolDto) {

        RolDto savedRol = rolService.saveRol(rolDto);
        BaseResponse<RolDto> rolBaseResponse = new BaseResponse<>();
        return rolBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Rol saved successfully", savedRol);
    }

    /**
     * Handler method for fetching a single Rol by its ID.
     * @param rolId String
     * @return ResponseEntity Rol
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{rolId}")
    public ResponseEntity<? extends RolDto> findByRolId(@PathVariable final String rolId) {
        RolDto retrievedRol = rolService.findRolById(rolId);
        return new ResponseEntity<>(retrievedRol, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted Rol with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse RolDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<RolDto>> getRoles(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "rolId, desc") String[] sort){

        Page<RolDto> rolDtoPage = rolService
                .findPaginatedSortedRol(page, size, sort);


        PageResponseDto<RolDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(rolDtoPage.getSize(), rolDtoPage.getNumberOfElements(),
                rolDtoPage.getTotalPages(), rolDtoPage.getNumber(), rolDtoPage.getContent());
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
    public ResponseEntity<? extends Response<RolDto>> updateRol(@RequestBody @Valid RolDto rolDto) {
        RolDto updateRol = rolService.updateRol(rolDto);
        BaseResponse<RolDto> rolDtoBaseResponse = new BaseResponse<>();
        return rolDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Rol Update Successfully", updateRol);
    }

    /**
     * Handler method for deleting a Rol by its ID.
     * @param rolId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{rolId}")
    public ResponseEntity<? extends Response<String>> deleteRol(@PathVariable final String rolId) {
        rolService.deleteRol(rolId);
        BaseResponse<String> rolResponse = new BaseResponse<>();
        return rolResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Rol with ID: ")
                        .append(rolId)
                        .append(" was deleted.").toString(), rolId);
    }
}

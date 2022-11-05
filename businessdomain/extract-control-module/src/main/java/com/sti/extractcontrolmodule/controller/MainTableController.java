package com.sti.extractcontrolmodule.controller;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.dto.MainTableDto;
import com.sti.extractcontrolmodule.dto.pageable.PageResponse;
import com.sti.extractcontrolmodule.dto.pageable.PageResponseDto;
import com.sti.extractcontrolmodule.response.BaseResponse;
import com.sti.extractcontrolmodule.response.Response;
import com.sti.extractcontrolmodule.response.error.ValidationError;
import com.sti.extractcontrolmodule.service.MainTableService;
import com.sti.extractcontrolmodule.utils.report.ExtractReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller for ExtractedLogModel entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "MainTable APIs")
@RestController
@RequestMapping(path = "/api/v1/main")
@RequiredArgsConstructor
public class MainTableController {

    private final MainTableService mainTableService;


    /**
     * Handler method for saving Validated given MainTableDto.
     * @return ResponseEntity Response MainTableDto
     */

    @ApiOperation(value = "Return all transaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping()
    public ResponseEntity<? extends Response<MainTableDto>> saveMainTable(@RequestBody @Valid MainTableDto mainTableDto){
        BaseResponse<MainTableDto> moduleBaseResponse = new BaseResponse<>();

        /**
         * This condition is to verify if the registration already exists
         */
        //System.out.println(this.mainTableService.existsAttribute(mainTableDto.getRegistrationNumber()));
        if(this.mainTableService.existsAttribute(mainTableDto.getRegistrationNumber())){
            ValidationError validationError = new ValidationError("ExtractedLog", mainTableDto.getRegistrationNumber(),mainTableDto,"The license plate already exists and cannot be used to enter it");
            return moduleBaseResponse.buildResponseEntity(HttpStatus.CONFLICT,"Conflict with the data to be validated, Registration " + mainTableDto.getRegistrationNumber() +  " already exists",mainTableDto);
        }

        MainTableDto saveMainTable = this.mainTableService.saveMainTable(mainTableDto);

        return moduleBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "MainTable saved successfully",saveMainTable);
    }

    /**
     * Handler method for fetching a single MainTableModel by its ID
     * @param mainId String
     * @return ResponseEntity MainTableDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })

    @GetMapping(value = "{mainId}")
    public ResponseEntity<? extends MainTableDto> findByMainTableId(@PathVariable("mainId") final String mainId){
        MainTableDto mainTableDto = this.mainTableService.findMainTableById(mainId);
        return new ResponseEntity<>(mainTableDto,HttpStatus.OK);
    }

    /**
     * Get Paginated sorted MainTableDto with given criteria
     * @param page Page number
     * @param size Page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse MainTableDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<MainTableDto>> getMMainTable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "idMain, desc") String[] sort
    ){
        Page<MainTableDto> mainTableDtoPage = this.mainTableService
                .findPaginatedSortedMainTable(page, size, sort);

        PageResponseDto<MainTableDto> pageResponseDto = new PageResponseDto<>();

        return pageResponseDto.buildResponseEntity(mainTableDtoPage.getSize(),mainTableDtoPage.getNumberOfElements(),
                mainTableDtoPage.getTotalPages(),mainTableDtoPage.getNumber(),mainTableDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given MainTableModel.
     * @return ResponseEntity Response MainTableDto
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping
    public ResponseEntity<? extends Response<MainTableDto>> updateMainTable(@RequestBody @Valid MainTableDto mainTableDto){
        MainTableDto updateMainTable = this.mainTableService.updateMainTable(mainTableDto);
        BaseResponse<MainTableDto> moduleBaseResponse = new BaseResponse<>();
        return moduleBaseResponse.buildResponseEntity(HttpStatus.OK, "MainTable Update Successfully", updateMainTable);
    }

    /**
     * Route to activate the registration of an main table
     * @param mainId String
     * @return Object
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(path = "/{mainId}/activate")
    public ResponseEntity<? extends Response<MainTableDto>> activateExtractedLogRegistrationNumber(@PathVariable("mainId") final String mainId){

        MainTableDto mainTableDto = this.mainTableService.activateMainTableRegistrationNumber(mainId);

        BaseResponse<MainTableDto> moduleBaseResponse = new BaseResponse<>();
        return moduleBaseResponse.buildResponseEntity(HttpStatus.OK, "The registration of the extract log was successfully updated", mainTableDto);
    }

    /**
     * Handler method for deleting a MainTableModel by its ID
     * @param mainId String
     * @return Response null
     */
    @ApiOperation(value = "Return all trabsaction budled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping(value = "{mainId}")
    public ResponseEntity<? extends Response<String>> deleteMainTable(@PathVariable("mainId") final String mainId){
        this.mainTableService.deleteMainTable(mainId);
        BaseResponse<String> extractedLogResponse = new BaseResponse<>();
        return extractedLogResponse
                .buildResponseEntity(HttpStatus.OK,new StringBuilder("MainTable with ID: ")
                        .append(mainId)
                        .append(" was deleted.").toString(),mainId);
    }
}
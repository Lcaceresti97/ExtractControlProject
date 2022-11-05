package com.sti.extractcontrolmodule.controller;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.dto.pageable.PageResponse;
import com.sti.extractcontrolmodule.dto.pageable.PageResponseDto;
import com.sti.extractcontrolmodule.model.ExtractedLogModel;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import com.sti.extractcontrolmodule.response.BaseResponse;
import com.sti.extractcontrolmodule.response.Response;
import com.sti.extractcontrolmodule.response.error.ErrorResponse;
import com.sti.extractcontrolmodule.response.error.ValidationError;
import com.sti.extractcontrolmodule.service.ExtractedLogService;
import com.sti.extractcontrolmodule.utils.report.ExtractReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "ExtractedLog APIs")
@RestController
@RequestMapping(path = "/api/v1/extract")
@RequiredArgsConstructor
public class ExtractedLogController {

    //@Autowired
    private final ExtractedLogService extractedLogService;

    /**
     * Handler method for saving Validated given ExtractedLogDto.
     * @return ResponseEntity Response ExtractedLogDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping()
    public ResponseEntity<? extends Response<ExtractedLogDto>> saveExtractedLog(@RequestBody @Valid ExtractedLogDto extractedLogDto){
        BaseResponse<ExtractedLogDto> moduleBaseResponse = new BaseResponse<>();

        /**
         * This condition is to verify if the registration already exists
         */
        //System.out.println(this.extractedLogService.existsAttribute(extractedLogDto.getRegistrationNumber()));
        if(this.extractedLogService.existsAttribute(extractedLogDto.getRegistrationNumber())){
            ValidationError validationError = new ValidationError("ExtractedLog", extractedLogDto.getRegistrationNumber(),extractedLogDto,"The license plate already exists and cannot be used to enter it");
            return moduleBaseResponse.buildResponseEntity(HttpStatus.CONFLICT,"Conflict with the data to be validated, Registration " + extractedLogDto.getRegistrationNumber() +  " already exists",extractedLogDto);
        }

        ExtractedLogDto saveExtractedLogDto = this.extractedLogService.saveExtractedLog(extractedLogDto);

        return moduleBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "ExtractedLog saved successfully",saveExtractedLogDto);
    }

    /**
     * Handler method for fetching a single ExtractedLogModel by its ID
     * @param extractedId String
     * @return ResponseEntity ExtractedLogDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })

    @GetMapping(value = "{extractedId}")
    public ResponseEntity<? extends ExtractedLogDto> findByExtractedLogId(@PathVariable("extractedId") final String extractedId){
        ExtractedLogDto extractedLogDto = this.extractedLogService.findExtractedLogById(extractedId);
        return new ResponseEntity<>(extractedLogDto,HttpStatus.OK);
    }

    /**
     * Get Paginated sorted ExtractedLogDto with given criteria
     * @param page Page number
     * @param size Page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse ExtractedLogDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<ExtractedLogDto>> getMExtractedLog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "extractedId, desc") String[] sort
    ){
        Page<ExtractedLogDto> extractedLogDtoPage = this.extractedLogService
                .findPaginatedSortedExtractedLog(page,size,sort);

        PageResponseDto<ExtractedLogDto> pageResponseDto = new PageResponseDto<>();

        return pageResponseDto.buildResponseEntity(extractedLogDtoPage.getSize(),extractedLogDtoPage.getNumberOfElements(),
                extractedLogDtoPage.getTotalPages(),extractedLogDtoPage.getNumber(),extractedLogDtoPage.getContent());
    }

    @GetMapping(path = "{userNameId}/export", params = {"fecha"})
    public ResponseEntity<?> exportPdfExtractedLog(@PathVariable(value = "userNameId") String userNameId,@RequestParam(name = "fecha") Date fecha, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=ExtractedLog_" + fechaActual + ".pdf";
        response.setHeader(cabecera,valor);

        List<ExtractedLogDto> extractedLogDtoList = this.extractedLogService.findExtractedLogByStatusAndDateExtractAndUserNameId(userNameId, fecha);


        if(extractedLogDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No records related to your user found");
        }

        for(ExtractedLogDto extractedLogDto : extractedLogDtoList){
            System.out.println(extractedLogDto.toString());
        }


        //ExtractReport extractReport = new ExtractReport(extractedLogDtoList);
        //extractReport.export(response);
        return ResponseEntity.status(HttpStatus.OK).body("Records exported to PDF");
    }

    /**
     * Handler method for update Validated given ExtractedLogModel.
     * @return ResponseEntity Response ExtractedLogDto
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping
    public ResponseEntity<? extends Response<ExtractedLogDto>> updateExtractedLog(@RequestBody @Valid ExtractedLogDto extractedLogDto){
        ExtractedLogDto updateExtractedLogDto = this.extractedLogService.updateExtractedLog(extractedLogDto);
        BaseResponse<ExtractedLogDto> moduleBaseResponse = new BaseResponse<>();
        return moduleBaseResponse.buildResponseEntity(HttpStatus.OK, "ExtractedLog Update Successfully", updateExtractedLogDto);
    }

    /**
     * Route to activate the registration of an extract log
     * @param extractedId String
     * @return Object
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping(path = "/{extractedId}/activate")
    public ResponseEntity<? extends Response<Object>> activateExtractedLogRegistrationNumber(@PathVariable("extractedId") String extractedId){

        ExtractedLogDto extractedLogDto = this.extractedLogService.activateExtractedLogRegistrationNumber(extractedId);

        BaseResponse<Object> moduleBaseResponse = new BaseResponse<>();
        return moduleBaseResponse.buildResponseEntity(HttpStatus.OK, "The registration of the extract log was successfully updated", extractedLogDto);
    }

    /**
     * Handler method for deleting a ExtractedLogModel by its ID
     * @param extractedId String
     * @return Response null
     */
    @ApiOperation(value = "Return all trabsaction budled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping(value = "{extractedId}")
    public ResponseEntity<? extends Response<String>> deleteExtractedLog(@PathVariable("extractedId") final String extractedId){
        this.extractedLogService.deleteExtractedLog(extractedId);
        BaseResponse<String> extractedLogResponse = new BaseResponse<>();
        return extractedLogResponse
                .buildResponseEntity(HttpStatus.OK,new StringBuilder("ExtractedLog with ID: ")
                        .append(extractedId)
                        .append(" was deleted.").toString(),extractedId);
    }
}

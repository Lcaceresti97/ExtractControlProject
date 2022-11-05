package com.sti.extractcontrolmodule.controller;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import com.sti.extractcontrolmodule.dto.pageable.PageResponse;
import com.sti.extractcontrolmodule.dto.pageable.PageResponseDto;
import com.sti.extractcontrolmodule.response.BaseResponse;
import com.sti.extractcontrolmodule.response.Response;
import com.sti.extractcontrolmodule.response.error.ValidationError;
import com.sti.extractcontrolmodule.service.QualityControllerLogService;
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
 * Controller for QualityControllerLogModel entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "QualityControllerLog APIs")
@RestController
@RequestMapping(path = "/api/v1/qualitycontrollerlog")
@RequiredArgsConstructor
public class QualityControllerLogController {

    private final QualityControllerLogService qualityControllerLogService;

    /**
     * Handler method for saving Validated given QualityControllerLogDto.
     * @return ResponseEntity Response QualityControllerLogDto
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping()
    public ResponseEntity<? extends Response<Object>> saveQualityControllerLog(@RequestBody @Valid QualityControllerLogDto qualityControllerLogDto){
        BaseResponse<Object> moduleBaseResponse = new BaseResponse<>();
        /**
         * This condition is to verify if the registration already exists
         */
        if(this.qualityControllerLogService.existsAttribute(qualityControllerLogDto.getRegistrationNumber())){
            ValidationError validationError = new ValidationError("QualityControllerLog", qualityControllerLogDto.getRegistrationNumber(),qualityControllerLogDto,"The license plate already exists and cannot be used to enter it");
            return moduleBaseResponse.buildResponseEntity(HttpStatus.CONFLICT,"Conflict with the data to be validated",validationError);
        }
        QualityControllerLogDto saveQualityControllerLogDto = this.qualityControllerLogService.saveQualityControllerLog(qualityControllerLogDto);
        return moduleBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "QualityControllerLog saved successfully",saveQualityControllerLogDto);
    }

    /**
     * Handler method for fetching a single QualityControllerLogModel by its ID
     * @param qualityControllerLogId String
     * @return ResponseEntity QualityControllerLogDto
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(value = "{qualityControllerLogId}")
    public ResponseEntity<? extends QualityControllerLogDto> findByQualityControllerLogId(@PathVariable("qualityControllerLogId") final String qualityControllerLogId){
        QualityControllerLogDto qualityControllerLogDto = this.qualityControllerLogService.findQualityControllerLogById(qualityControllerLogId);
        return new ResponseEntity<>(qualityControllerLogDto,HttpStatus.OK);
    }

    /**
     * Get Paginated sorted QualityControllerLogDto with given criteria
     * @param page Page number
     * @param size Page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse QualityControllerLogDto
     */
    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<QualityControllerLogDto>> getMQualityControllerLog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "qualityControllerLogId, desc") String[] sort
    ){
        Page<QualityControllerLogDto> qualityControllerLogDtoPage = this.qualityControllerLogService.findPaginatedSortedQualityControllerLog(page,size,sort);

        PageResponseDto<QualityControllerLogDto> pageResponseDto = new PageResponseDto<>();

        return pageResponseDto.buildResponseEntity(qualityControllerLogDtoPage.getSize(),qualityControllerLogDtoPage.getNumberOfElements(),
                qualityControllerLogDtoPage.getTotalPages(),qualityControllerLogDtoPage.getNumber(),qualityControllerLogDtoPage.getContent());
    }

    /**
     *
     * @param userNameId
     * @param fecha
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(path = "{userNameId}/export", params = {"fecha"})
    public ResponseEntity<?> exportPdfQualityControllerLog(@PathVariable(value = "userNameId") String userNameId, @RequestParam(name = "fecha") Date fecha, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Control de Calidad_" + fechaActual + ".pdf";
        response.setHeader(cabecera,valor);

        List<QualityControllerLogDto> qualityControllerLogDtos = this.qualityControllerLogService.findQualityControllerLogByStatusAndDateQualityControllerAndUserNameId(userNameId,fecha);

        if(qualityControllerLogDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No records related to your user found");
        }

        ExtractReport extractReport = new ExtractReport(qualityControllerLogDtos);
        extractReport.export(response);
        return ResponseEntity.status(HttpStatus.OK).body("Records exported to PDF");
    }




    /**
     * Handler method for update Validated given QualityControllerLogModel.
     * @return ResponseEntity Response QualityControllerLogDto
     */

    @ApiOperation(value = "Return all trabsaction budled inti Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping
    public ResponseEntity<? extends Response<QualityControllerLogDto>> updateQualityControllerLog(@RequestBody @Valid QualityControllerLogDto qualityControllerLogDto){
        QualityControllerLogDto updateQualityControllerLogDto = this.qualityControllerLogService.updateQualityControllerLog(qualityControllerLogDto);
        BaseResponse<QualityControllerLogDto> moduleBaseResponse = new BaseResponse<>();
        return moduleBaseResponse.buildResponseEntity(HttpStatus.OK, "QualityControllerLog Update Successfully", updateQualityControllerLogDto);
    }

    /**
     * Handler method for deleting a QualityControllerLogModel by its ID
     * @param qualityControllerLogId String
     * @return Response null
     */
    @ApiOperation(value = "Return all trabsaction budled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping(value = "{qualityControllerLogId}")
    public ResponseEntity<? extends Response<String>> deleteQualityControllerLog(@PathVariable("qualityControllerLogId") final String qualityControllerLogId){
        this.qualityControllerLogService.deleteQualityControllerLog(qualityControllerLogId);
        BaseResponse<String> extractedLogResponse = new BaseResponse<>();
        return extractedLogResponse
                .buildResponseEntity(HttpStatus.OK,new StringBuilder("QualityControllerLog with ID: ")
                        .append(qualityControllerLogId)
                        .append(" was deleted.").toString(),qualityControllerLogId);
    }

}

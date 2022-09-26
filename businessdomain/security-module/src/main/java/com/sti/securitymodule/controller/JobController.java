package com.sti.securitymodule.controller;

import com.sti.securitymodule.dto.JobDto;
import com.sti.securitymodule.dto.pageable.PageResponse;
import com.sti.securitymodule.dto.pageable.PageResponseDto;
import com.sti.securitymodule.response.BaseResponse;
import com.sti.securitymodule.response.Response;
import com.sti.securitymodule.service.JobService;
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
 * Controller for Job entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Job APIs")
@RestController
@RequestMapping(path = "/api/v1/security/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /**
     * Handler method for saving Validated given Job.
     * @return ResponseEntity Response Job
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<JobDto>> saveJob(@RequestBody @Valid JobDto jobDto) {

        JobDto savedJob = jobService.saveJob(jobDto);
        BaseResponse<JobDto> jobBaseResponse = new BaseResponse<>();
        return jobBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Job saved successfully", savedJob);
    }

    /**
     * Handler method for fetching a single Job by its ID.
     * @param jobId String
     * @return ResponseEntity Job
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{jobId}")
    public ResponseEntity<? extends JobDto> findByJobId(@PathVariable final String jobId) {
        JobDto retrievedJob = jobService.findJobById(jobId);
        return new ResponseEntity<>(retrievedJob, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted Job with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse JobDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<JobDto>> getJobs(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "jobId, desc") String[] sort){

        Page<JobDto> jobDtoPage = jobService
                .findPaginatedSortedJob(page, size, sort);


        PageResponseDto<JobDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(jobDtoPage.getSize(), jobDtoPage.getNumberOfElements(),
                jobDtoPage.getTotalPages(), jobDtoPage.getNumber(), jobDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given Job.
     * @return ResponseEntity Response Job
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<JobDto>> updateJob(@RequestBody @Valid JobDto jobDto) {
        JobDto updateJob = jobService.updateJob(jobDto);
        BaseResponse<JobDto> jobDtoBaseResponse = new BaseResponse<>();
        return jobDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Job Update Successfully", updateJob);
    }

    /**
     * Handler method for deleting a Job by its ID.
     * @param jobId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity<? extends Response<String>> deleteJob(@PathVariable final String jobId) {
        jobService.deleteJob(jobId);
        BaseResponse<String> jobResponse = new BaseResponse<>();
        return jobResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Job with ID: ")
                        .append(jobId)
                        .append(" was deleted.").toString(), jobId);
    }
}

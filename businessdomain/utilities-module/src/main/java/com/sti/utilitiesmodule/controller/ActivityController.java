package com.sti.utilitiesmodule.controller;

import com.sti.utilitiesmodule.dto.ActivityDto;
import com.sti.utilitiesmodule.dto.pageable.PageResponse;
import com.sti.utilitiesmodule.dto.pageable.PageResponseDto;
import com.sti.utilitiesmodule.response.BaseResponse;
import com.sti.utilitiesmodule.response.Response;
import com.sti.utilitiesmodule.service.ActivityService;
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
 * Controller for Activity entity operations.
 *
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Api(tags = "Activity APIs")
@RestController
@RequestMapping(path = "/api/v1/utilities/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    /**
     * Handler method for saving Validated given Activity.
     * @return ResponseEntity Response Activity
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PostMapping
    public ResponseEntity<? extends Response<ActivityDto>> saveActivity(@RequestBody @Valid ActivityDto activityDto) {

        ActivityDto savedActivity = activityService.saveActivity(activityDto);
        BaseResponse<ActivityDto> activityBaseResponse = new BaseResponse<>();
        return activityBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Activity saved successfully", savedActivity);
    }

    /**
     * Handler method for fetching a single Activity by its ID.
     * @param activityId String
     * @return ResponseEntity Activity
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "/{activityId}")
    public ResponseEntity<? extends ActivityDto> findByActivityId(@PathVariable final String activityId) {
        ActivityDto retrievedActivity = activityService.findActivityById(activityId);
        return new ResponseEntity<>(retrievedActivity, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted Activity with given criteria.
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse ActivityDto
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<ActivityDto>> getActivities(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "activityId, desc") String[] sort){

        Page<ActivityDto> activityDtoPage = activityService
                .findPaginatedSortedActivity(page, size, sort);


        PageResponseDto<ActivityDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(activityDtoPage.getSize(), activityDtoPage.getNumberOfElements(),
                activityDtoPage.getTotalPages(), activityDtoPage.getNumber(), activityDtoPage.getContent());
    }

    /**
     * Handler method for update Validated given Activity.
     * @return ResponseEntity Response Activity
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @PutMapping
    public ResponseEntity<? extends Response<ActivityDto>> updateActivity(@RequestBody @Valid ActivityDto activityDto) {
        ActivityDto updateActivity = activityService.updateActivity(activityDto);
        BaseResponse<ActivityDto> activityDtoBaseResponse = new BaseResponse<>();
        return activityDtoBaseResponse
                .buildResponseEntity(HttpStatus.OK, "Activity Update Successfully", updateActivity);
    }


    /**
     * Handler method for deleting a Activity by its ID.
     * @param activityId String
     * @return Response null
     */
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 204, message = "There are not transactions"),
            @ApiResponse(code = 500, message = "Internal error")})
    @DeleteMapping(value = "/{activityId}")
    public ResponseEntity<? extends Response<String>> deleteActivity(@PathVariable final String activityId) {
        activityService.deleteActivity(activityId);
        BaseResponse<String> activityResponse = new BaseResponse<>();
        return activityResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Activity with ID: ")
                        .append(activityId)
                        .append(" was deleted.").toString(), activityId);
    }
}

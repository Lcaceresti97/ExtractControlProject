package com.sti.utilitiesmodule.service.implementation;

import com.sti.utilitiesmodule.dto.ActivityDto;
import com.sti.utilitiesmodule.exception.ActivityNotFoundException;
import com.sti.utilitiesmodule.model.Activity;
import com.sti.utilitiesmodule.model.mapper.ActivityMapper;
import com.sti.utilitiesmodule.model.status.ModelStatus;
import com.sti.utilitiesmodule.repository.ActivityRepository;
import com.sti.utilitiesmodule.service.ActivityService;
import com.sti.utilitiesmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Activity entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public ActivityDto saveActivity(ActivityDto activityDto) {
        Activity activity = Activity.buildActivityFromDto(this.activityMapper.dtoToActivity(activityDto));
        this.activityRepository.save(activity);
        return activityMapper.activityToDto(activity);
    }

    @Override
    public ActivityDto findActivityById(String activityId) throws ActivityNotFoundException {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> ActivityNotFoundException.buildActivityNotFoundExceptionForId(activityId));
        return activityMapper.activityToDto(isActiveActivity(activity,"activityId", activityId));
    }

    @Override
    public Page<ActivityDto> findPaginatedSortedActivity(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<ActivityDto> activityDtos;
        activityDtos = activityMapper
                .activityToToDto(activityRepository.findAll(pageable).toList());

        return new PageImpl<>(activityDtos);
    }

    @Override
    public ActivityDto updateActivity(ActivityDto activityDto) {
        Activity activity = this.activityMapper.dtoToActivity(activityDto);
        this.activityRepository.save(activity);
        return activityMapper.activityToDto(activity);
    }

    @Override
    public void deleteActivity(String activityId) {
        Activity activity = activityMapper.dtoToActivity(findActivityById(activityId));
        activity.setActivityStatus(ModelStatus.INACTIVE);
        activityRepository.save(activity);
    }

    /**
     * Return Activity if status code is ACTIVE.
     * @param activity Activity
     * @param queryField String
     * @param queryFieldValue String
     * @return Activity
     * @throws ActivityNotFoundException ex
     */
    private Activity isActiveActivity(Activity activity, String queryField, String queryFieldValue){
        if(activity.getActivityStatus().getStatusCode() == 0){
            return activity;
        }
        throw ActivityNotFoundException
                .buildActivityNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

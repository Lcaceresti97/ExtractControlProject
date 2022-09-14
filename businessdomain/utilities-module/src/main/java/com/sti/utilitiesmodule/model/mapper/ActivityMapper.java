package com.sti.utilitiesmodule.model.mapper;

import com.sti.utilitiesmodule.dto.ActivityDto;
import com.sti.utilitiesmodule.model.Activity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity dtoToActivity(ActivityDto dto);

    ActivityDto activityToDto(Activity activity);

    List<Activity> dtoToActivity(List<ActivityDto> dtos);

    List<ActivityDto> activityToToDto(List<Activity> activities);
}

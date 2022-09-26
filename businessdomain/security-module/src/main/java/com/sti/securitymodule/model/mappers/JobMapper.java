package com.sti.securitymodule.model.mappers;

import com.sti.securitymodule.dto.JobDto;
import com.sti.securitymodule.model.Job;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job dtoToJob(JobDto dto);

    JobDto jobToDto(Job job);

    List<Job> dtoToJob(List<JobDto> dtos);

    List<JobDto> jobToDto(List<Job> jobs);
}

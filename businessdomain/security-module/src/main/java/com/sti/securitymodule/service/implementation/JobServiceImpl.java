package com.sti.securitymodule.service.implementation;

import com.sti.securitymodule.dto.JobDto;
import com.sti.securitymodule.exception.JobNotFoundException;
import com.sti.securitymodule.model.Job;
import com.sti.securitymodule.model.mappers.JobMapper;
import com.sti.securitymodule.model.status.ModelStatus;
import com.sti.securitymodule.repository.JobRepository;
import com.sti.securitymodule.service.JobService;
import com.sti.securitymodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Job entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public JobDto saveJob(JobDto jobDto) {
        Job job = Job.buildFromDtoJob(this.jobMapper.dtoToJob(jobDto));
        this.jobRepository.save(job);
        return jobMapper.jobToDto(job);
    }

    @Override
    public JobDto findJobById(String jobId) throws JobNotFoundException {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> JobNotFoundException.buildJobNotFoundExceptionForId(jobId));
        return jobMapper.jobToDto(isActiveJob(job,"jobId", jobId));
    }

    @Override
    public Page<JobDto> findPaginatedSortedJob(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<JobDto> jobDtos;
        jobDtos = jobMapper
                .jobToDto(jobRepository.findAll(pageable).toList());

        return new PageImpl<>(jobDtos);
    }

    @Override
    public JobDto updateJob(JobDto jobDto) {
        Job job = this.jobMapper.dtoToJob(jobDto);
        this.jobRepository.save(job);
        return jobMapper.jobToDto(job);
    }

    @Override
    public void deleteJob(String jobId) {
        Job job = jobMapper.dtoToJob(findJobById(jobId));
        job.setJobStatus(ModelStatus.INACTIVE);
        jobRepository.save(job);
    }

    /**
     * Return Job if status code is ACTIVE.
     * @param job Job
     * @param queryField String
     * @param queryFieldValue String
     * @return Job
     * @throws JobNotFoundException ex
     */
    private Job isActiveJob(Job job, String queryField, String queryFieldValue){
        if(job.getJobStatus().getStatusCode() == 0){
            return job;
        }
        throw JobNotFoundException
                .buildJobNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

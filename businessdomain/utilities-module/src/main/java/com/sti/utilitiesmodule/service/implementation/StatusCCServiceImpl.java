package com.sti.utilitiesmodule.service.implementation;

import com.sti.utilitiesmodule.dto.StatusCCDto;
import com.sti.utilitiesmodule.exception.StatusCCNotFoundException;
import com.sti.utilitiesmodule.model.StatusCC;
import com.sti.utilitiesmodule.model.mapper.StatusCCMapper;
import com.sti.utilitiesmodule.model.status.ModelStatus;
import com.sti.utilitiesmodule.repository.StatusCCRepository;
import com.sti.utilitiesmodule.service.StatusCCService;
import com.sti.utilitiesmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for StatusCC entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class StatusCCServiceImpl implements StatusCCService {

    private final StatusCCRepository statusCCRepository;

    private final StatusCCMapper statusCCMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public StatusCCDto saveStatusCC(StatusCCDto statusCCDto) {
        StatusCC statusCC = StatusCC.buildStatusFromDto(this.statusCCMapper.dtoToStatusCC(statusCCDto));
        this.statusCCRepository.save(statusCC);
        return statusCCMapper.statusCCToDto(statusCC);
    }

    @Override
    public StatusCCDto findMStatusCCById(String statusCCId) throws StatusCCNotFoundException {
        StatusCC statusCC = statusCCRepository.findById(statusCCId).orElseThrow(() -> StatusCCNotFoundException.buildStatusCCNotFoundExceptionForId(statusCCId));
        return statusCCMapper.statusCCToDto(isActiveStatusCC(statusCC,"statusCCId", statusCCId));
    }

    @Override
    public Page<StatusCCDto> findPaginatedSortedStatusCC(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<StatusCCDto> statusCCDtos;
        statusCCDtos = statusCCMapper
                .statusCCToToDto(statusCCRepository.findAll(pageable).toList());

        return new PageImpl<>(statusCCDtos);
    }

    @Override
    public StatusCCDto updateStatusCC(StatusCCDto statusCCDto) {
        StatusCC statusCC = this.statusCCMapper.dtoToStatusCC(statusCCDto);
        this.statusCCRepository.save(statusCC);
        return statusCCMapper.statusCCToDto(statusCC);
    }

    @Override
    public void deleteStatusCC(String statusCCId) {
        StatusCC statusCC = statusCCMapper.dtoToStatusCC(findMStatusCCById(statusCCId));
        statusCC.setCcStatus(ModelStatus.INACTIVE);
        statusCCRepository.save(statusCC);
    }

    /**
     * Return StatusCC if status code is ACTIVE.
     * @param statusCC StatusCC
     * @param queryField String
     * @param queryFieldValue String
     * @return StatusCC
     * @throws StatusCCNotFoundException ex
     */
    private StatusCC isActiveStatusCC(StatusCC statusCC, String queryField, String queryFieldValue){
        if(statusCC.getCcStatus().getStatusCode() == 0){
            return statusCC;
        }
        throw StatusCCNotFoundException
                .buildStatusCCNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

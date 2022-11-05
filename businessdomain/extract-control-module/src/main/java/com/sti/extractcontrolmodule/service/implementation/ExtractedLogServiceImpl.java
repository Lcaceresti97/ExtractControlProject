package com.sti.extractcontrolmodule.service.implementation;

import com.sti.extractcontrolmodule.dto.ExtractedLogDto;
import com.sti.extractcontrolmodule.exception.ExtractedLogNotFoundException;
import com.sti.extractcontrolmodule.model.ExtractedLogModel;
import com.sti.extractcontrolmodule.model.mapper.ExtractedLogMapper;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import com.sti.extractcontrolmodule.repository.ExtraxtedLogRepository;
import com.sti.extractcontrolmodule.service.ExtractedLogService;
import com.sti.extractcontrolmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Service class for ExtractedLogModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ExtractedLogServiceImpl implements ExtractedLogService {

    private final ExtractedLogMapper extractedLogMapper;

    private final ExtraxtedLogRepository extraxtedLogRepository;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public ExtractedLogDto saveExtractedLog(ExtractedLogDto extractedLogDto) {
        ExtractedLogModel extractedLogModel = ExtractedLogModel.buildExtractedLogFromDto(this.extractedLogMapper.dtoToExtractedLogModel(extractedLogDto));
        this.extraxtedLogRepository.save(extractedLogModel);

        return this.extractedLogMapper.extractedLogModelToDto(extractedLogModel);
    }

    @Override
    public ExtractedLogDto findExtractedLogById(final String extractedId) throws ExtractedLogNotFoundException {
        ExtractedLogModel extractedLogModel = this.extraxtedLogRepository.findById(extractedId).orElseThrow(()->ExtractedLogNotFoundException.buildExtractedLogNotFoundExceptionForId(extractedId));
        return this.extractedLogMapper.extractedLogModelToDto(extractedLogModel);
    }

    @Override
    public Page<ExtractedLogDto> findPaginatedSortedExtractedLog(int page, int size, String[] sort) {
        List<Sort.Order> orders = this.sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page,size,Sort.by(orders));
        List<ExtractedLogDto> extractedLogDtos;

        extractedLogDtos = this.extractedLogMapper.extractedLogModelToDto(this.extraxtedLogRepository.findAll(pageable).toList());

        return new PageImpl<>(extractedLogDtos);
    }

    @Override
    public ExtractedLogDto updateExtractedLog(ExtractedLogDto extractedLogDto) {
        ExtractedLogModel extractedLogModel = this.extractedLogMapper.dtoToExtractedLogModel(extractedLogDto);
        this.extraxtedLogRepository.save(extractedLogModel);
        return this.extractedLogMapper.extractedLogModelToDto(extractedLogModel);
    }

    @Override
    public void deleteExtractedLog(final String extractedId) {
        ExtractedLogModel extractedLogModel = this.extractedLogMapper.dtoToExtractedLogModel(findExtractedLogById(extractedId));
        extractedLogModel.setStatusExtractLog(ModelStatus.INACTIVE);
        this.extraxtedLogRepository.save(extractedLogModel);
    }

    @Override
    public boolean existsAttribute(final String attribute) {
        return this.extraxtedLogRepository.existsByRegistrationNumber(attribute);
    }

    @Override
    public ExtractedLogDto activateExtractedLogRegistrationNumber(final String extractedId) {
        ExtractedLogModel extractedLogModel = this.extractedLogMapper.dtoToExtractedLogModel(findExtractedLogById(extractedId));
        extractedLogModel.setStatus(ModelStatus.ACTIVE);
        extractedLogModel.setStatusExtractLog(ModelStatus.ACTIVE);
        return this.extractedLogMapper.extractedLogModelToDto(extractedLogModel);
    }

    @Override
    public List<ExtractedLogDto> findExtractedLogByStatusAndDateExtractAndUserNameId(final String userNameId, final Date fecha) {
        System.out.println(fecha);
        List<ExtractedLogDto> extractedLogDtos = this.extractedLogMapper.extractedLogModelToDto(this.extraxtedLogRepository.findByStatusExtractLogAndDateExtractAndUserNameId(ModelStatus.INACTIVE,fecha,userNameId));
        if(extractedLogDtos.isEmpty()){
            System.out.println("La lista está vacía");
        }
        return extractedLogDtos;
    }
}
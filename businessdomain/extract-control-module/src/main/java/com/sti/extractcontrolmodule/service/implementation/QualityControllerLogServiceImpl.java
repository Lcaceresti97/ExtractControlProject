package com.sti.extractcontrolmodule.service.implementation;

import com.sti.extractcontrolmodule.dto.QualityControllerLogDto;
import com.sti.extractcontrolmodule.exception.MainTableNotFoundException;
import com.sti.extractcontrolmodule.exception.QualityControllerLogException;
import com.sti.extractcontrolmodule.model.MainTableModel;
import com.sti.extractcontrolmodule.model.QualityControllerLogModel;
import com.sti.extractcontrolmodule.model.mapper.QualityControllerMapper;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import com.sti.extractcontrolmodule.repository.QualityControllerLogRepository;
import com.sti.extractcontrolmodule.service.QualityControllerLogService;
import com.sti.extractcontrolmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service class for QualityControllerLogModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class QualityControllerLogServiceImpl implements QualityControllerLogService {

    private final QualityControllerLogRepository qualityControllerLogRepository;

    private final QualityControllerMapper qualityControllerMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public QualityControllerLogDto saveQualityControllerLog(QualityControllerLogDto qualityControllerLogDto) {
        System.out.println(qualityControllerLogDto.toString());
        QualityControllerLogModel qualityControllerLogModel = QualityControllerLogModel.buildQualityControllerLogFromDto(this.qualityControllerMapper.dtoToQualityControllerLogModel(qualityControllerLogDto));
        System.out.println(qualityControllerLogModel.toString());
        //Si da error cpm eñ mapstruct también se puede la siguiente forma de mapear
        /*
        var qualityControllerLogModel2 = new QualityControllerLogModel();
        BeanUtils.copyProperties(qualityControllerLogDto,qualityControllerLogModel2);
        this.qualityControllerLogRepository.save(qualityControllerLogModel2);

        BeanUtils.copyProperties(qualityControllerLogModel2,qualityControllerLogDto);
        return qualityControllerLogDto;
        */
        this.qualityControllerLogRepository.save(qualityControllerLogModel);
        return this.qualityControllerMapper.qualityControllerLogModelToDto(qualityControllerLogModel);
    }

    @Override
    public QualityControllerLogDto findQualityControllerLogById(final String qualityControllerLogId) throws QualityControllerLogException {
        QualityControllerLogModel qualityControllerLogModel = this.qualityControllerLogRepository.findById(qualityControllerLogId).orElseThrow(()-> QualityControllerLogException.buildQualityControllerLogNotFoundExceptionForId(qualityControllerLogId));
        return this.qualityControllerMapper.qualityControllerLogModelToDto(qualityControllerLogModel);
    }

    @Override
    public Page<QualityControllerLogDto> findPaginatedSortedQualityControllerLog(int page, int size, String[] sort) {
        List<Sort.Order> orders = this.sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page,size,Sort.by(orders));
        List<QualityControllerLogDto> qualityControllerLogDtos;

        qualityControllerLogDtos = this.qualityControllerMapper.qualityControllerLogModelToDto(this.qualityControllerLogRepository.findAll(pageable).toList());

        return new PageImpl<>(qualityControllerLogDtos);
    }

    @Override
    public QualityControllerLogDto updateQualityControllerLog(QualityControllerLogDto qualityControllerLogDto) {
        QualityControllerLogModel qualityControllerLogModel = this.qualityControllerMapper.dtoToQualityControllerLogModel(qualityControllerLogDto);
        this.qualityControllerLogRepository.save(qualityControllerLogModel);
        return this.qualityControllerMapper.qualityControllerLogModelToDto(qualityControllerLogModel);
    }

    @Override
    public void deleteQualityControllerLog(String qualityControllerLogId) {
        QualityControllerLogModel qualityControllerLogModel = this.qualityControllerMapper.dtoToQualityControllerLogModel(findQualityControllerLogById(qualityControllerLogId));
        qualityControllerLogModel.setStatusQualityControllerLog(ModelStatus.INACTIVE);
        this.qualityControllerLogRepository.save(qualityControllerLogModel);
    }

    @Override
    public boolean existsAttribute(String attribute) {
        return this.qualityControllerLogRepository.existsByRegistrationNumber(attribute);
    }

    @Override
    public List<QualityControllerLogDto> findQualityControllerLogByStatusAndDateQualityControllerAndUserNameId(final String userNameId, final Date fecha) {
        System.out.println(fecha);
        List<QualityControllerLogDto> qualityControllerLogDtos = this.qualityControllerMapper.qualityControllerLogModelToDto(this.qualityControllerLogRepository.findByStatusQualityControllerLogAndDateQualityControllerAndUserNameId(ModelStatus.INACTIVE, fecha, userNameId));
        if(qualityControllerLogDtos.isEmpty()){
            System.out.println("La lista esta vacía");
        }
        return qualityControllerLogDtos;
    }


    /**
     * Return qualityControllerLogModel if status code is ACTIVE.
     * @param qualityControllerLogModel QualityControllerLogModel
     * @param queryField String
     * @param queryFieldValue String
     * @return QualityControllerLogModel
     * @throws QualityControllerLogException
     */
    private QualityControllerLogModel isActiveQualityControllerLogModel(QualityControllerLogModel qualityControllerLogModel, String queryField, String queryFieldValue){
        if(qualityControllerLogModel.getStatusQualityControllerLog().getStatusCode() == 0){
            return qualityControllerLogModel;
        }
        throw QualityControllerLogException.buildQualityControllerLogNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

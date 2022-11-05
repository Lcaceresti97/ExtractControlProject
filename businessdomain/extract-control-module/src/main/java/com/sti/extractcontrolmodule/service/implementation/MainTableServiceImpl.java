package com.sti.extractcontrolmodule.service.implementation;

import com.sti.extractcontrolmodule.dto.MainTableDto;
import com.sti.extractcontrolmodule.exception.MainTableNotFoundException;
import com.sti.extractcontrolmodule.model.MainTableModel;
import com.sti.extractcontrolmodule.model.mapper.MainTableMapper;
import com.sti.extractcontrolmodule.model.status.ModelStatus;
import com.sti.extractcontrolmodule.repository.MainTableRepository;
import com.sti.extractcontrolmodule.service.MainTableService;
import com.sti.extractcontrolmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for MainTableModel entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MainTableServiceImpl implements MainTableService {

    private final MainTableRepository mainTableRepository;

    private final MainTableMapper mainTableMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public MainTableDto saveMainTable(MainTableDto mainTableDto) {
        MainTableModel model = MainTableModel.buildMainTableFromDto(this.mainTableMapper.dtoToMainTableModel(mainTableDto));
        System.out.println(model.toString());
        this.mainTableRepository.save(model);
        return this.mainTableMapper.mainTableModelToDto(model);
    }

    @Override
    public MainTableDto findMainTableById(final String mainId) throws MainTableNotFoundException {
        MainTableModel mainTableModel = this.mainTableRepository.findById(mainId).orElseThrow(()-> MainTableNotFoundException.buildMainTableNotFoundExceptionForId(mainId));
        return this.mainTableMapper.mainTableModelToDto(mainTableModel);
    }

    @Override
    public Page<MainTableDto> findPaginatedSortedMainTable(int page, int size, String[] sort) {
        List<Sort.Order> orders  = this.sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page,size,Sort.by(orders));
        List<MainTableDto> mainTableDtos;

        mainTableDtos = this.mainTableMapper.mainTableModelToDto(this.mainTableRepository.findAll(pageable).toList());

        return new PageImpl<>(mainTableDtos);
    }

    @Override
    public MainTableDto updateMainTable(MainTableDto mainTableDto) {
        MainTableModel mainTableModel = this.mainTableMapper.dtoToMainTableModel(mainTableDto);
        this.mainTableRepository.save(mainTableModel);
        return this.mainTableMapper.mainTableModelToDto(mainTableModel);
    }

    @Override
    public void deleteMainTable(final String mainId) {
        MainTableModel mainTableModel = this.mainTableMapper.dtoToMainTableModel(findMainTableById(mainId));
        mainTableModel.setMainTableStatus(ModelStatus.INACTIVE);
        this.mainTableRepository.save(mainTableModel);
    }

    @Override
    public MainTableDto activateMainTableRegistrationNumber(final String mainId) {
        MainTableModel mainTableModel = this.mainTableMapper.dtoToMainTableModel(findMainTableById(mainId));
        mainTableModel.setStatusCCId(ModelStatus.ACTIVE);
        mainTableModel.setMainTableStatus(ModelStatus.ACTIVE);
        return this.mainTableMapper.mainTableModelToDto(mainTableModel);
    }

    @Override
    public boolean existsAttribute(final String attribute) {
        return this.mainTableRepository.existsByRegistrationNumber(attribute);
    }
}
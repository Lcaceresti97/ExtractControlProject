package com.sti.utilitiesmodule.service.implementation;

import com.sti.utilitiesmodule.dto.TractDto;
import com.sti.utilitiesmodule.exception.TractNotFoundException;
import com.sti.utilitiesmodule.model.Tract;
import com.sti.utilitiesmodule.model.mapper.TractMapper;
import com.sti.utilitiesmodule.model.status.ModelStatus;
import com.sti.utilitiesmodule.repository.TractRepository;
import com.sti.utilitiesmodule.service.TractService;
import com.sti.utilitiesmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Tract entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class TractServiceImpl implements TractService {

    private final TractRepository tractRepository;

    private final TractMapper tractMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public TractDto saveTract(TractDto tractDto) {
        Tract tract = Tract.buildTractFromDto(this.tractMapper.dtoToTract(tractDto));
        this.tractRepository.save(tract);
        return tractMapper.tractToDto(tract);
    }

    @Override
    public TractDto findTractById(String tractId) throws TractNotFoundException {
        Tract tract = tractRepository.findById(tractId).orElseThrow(() -> TractNotFoundException.buildTractNotFoundExceptionForId(tractId));
        return tractMapper.tractToDto(isActiveTract(tract,"tractId", tractId));
    }

    @Override
    public Page<TractDto> findPaginatedSortedTract(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<TractDto> tractDtos;
        tractDtos = tractMapper
                .tractToToDto(tractRepository.findAll(pageable).toList());

        return new PageImpl<>(tractDtos);
    }

    @Override
    public TractDto updateTract(TractDto tractDto) {
        Tract tract = this.tractMapper.dtoToTract(tractDto);
        this.tractRepository.save(tract);
        return tractMapper.tractToDto(tract);
    }

    @Override
    public void deleteTract(String tractId) {
        Tract tract = tractMapper.dtoToTract(findTractById(tractId));
        tract.setTractStatus(ModelStatus.INACTIVE);
        tractRepository.save(tract);
    }

    /**
     * Return Tract if status code is ACTIVE.
     * @param tract Tract
     * @param queryField String
     * @param queryFieldValue String
     * @return Tract
     * @throws TractNotFoundException ex
     */
    private Tract isActiveTract(Tract tract, String queryField, String queryFieldValue){
        if(tract.getTractStatus().getStatusCode() == 0){
            return tract;
        }
        throw TractNotFoundException
                .buildTractNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

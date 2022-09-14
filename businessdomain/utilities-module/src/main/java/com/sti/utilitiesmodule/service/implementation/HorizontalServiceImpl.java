package com.sti.utilitiesmodule.service.implementation;

import com.sti.utilitiesmodule.dto.HorizontalDto;
import com.sti.utilitiesmodule.exception.HorizontalNotFoundException;
import com.sti.utilitiesmodule.model.Horizontal;
import com.sti.utilitiesmodule.model.mapper.HorizontalMapper;
import com.sti.utilitiesmodule.model.status.ModelStatus;
import com.sti.utilitiesmodule.repository.HorizontalRepository;
import com.sti.utilitiesmodule.service.HorizontalService;
import com.sti.utilitiesmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Horizontal entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class HorizontalServiceImpl implements HorizontalService {

    private final HorizontalRepository horizontalRepository;

    private final HorizontalMapper horizontalMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public HorizontalDto saveHorizontal(HorizontalDto horizontalDto) {
        Horizontal horizontal = Horizontal.buildHorizontalFromDto(this.horizontalMapper.dtoToHorizontal(horizontalDto));
        this.horizontalRepository.save(horizontal);
        return horizontalMapper.horizontalToDto(horizontal);
    }

    @Override
    public HorizontalDto findHorizontalById(String horizontalId) throws HorizontalNotFoundException {
        Horizontal horizontal = horizontalRepository.findById(horizontalId).orElseThrow(() -> HorizontalNotFoundException.buildHorizontalNotFoundExceptionForId(horizontalId));
        return horizontalMapper.horizontalToDto(isActiveHorizontal(horizontal,"horizontalId", horizontalId));
    }

    @Override
    public Page<HorizontalDto> findPaginatedSortedHorizontal(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<HorizontalDto> horizontalDtos;
        horizontalDtos = horizontalMapper
                .horizontalToToDto(horizontalRepository.findAll(pageable).toList());

        return new PageImpl<>(horizontalDtos);
    }

    @Override
    public HorizontalDto updateHorizontal(HorizontalDto horizontalDto) {
        Horizontal horizontal = this.horizontalMapper.dtoToHorizontal(horizontalDto);
        this.horizontalRepository.save(horizontal);
        return horizontalMapper.horizontalToDto(horizontal);
    }

    @Override
    public void deleteHorizontal(String horizontalId) {
        Horizontal horizontal = horizontalMapper.dtoToHorizontal(findHorizontalById(horizontalId));
        horizontal.setHorizontalStatus(ModelStatus.INACTIVE);
        horizontalRepository.save(horizontal);
    }

    /**
     * Return Horizontal if status code is ACTIVE.
     * @param horizontal Horizontal
     * @param queryField String
     * @param queryFieldValue String
     * @return Horizontal
     * @throws HorizontalNotFoundException ex
     */
    private Horizontal isActiveHorizontal(Horizontal horizontal, String queryField, String queryFieldValue){
        if(horizontal.getHorizontalStatus().getStatusCode() == 0){
            return horizontal;
        }
        throw HorizontalNotFoundException
                .buildHorizontalNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

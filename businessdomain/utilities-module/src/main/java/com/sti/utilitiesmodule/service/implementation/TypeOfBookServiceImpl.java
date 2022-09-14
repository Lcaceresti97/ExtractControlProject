package com.sti.utilitiesmodule.service.implementation;

import com.sti.utilitiesmodule.dto.TypeOfBookDto;
import com.sti.utilitiesmodule.exception.TypeOfBookNotFoundException;
import com.sti.utilitiesmodule.model.TypeOfBook;
import com.sti.utilitiesmodule.model.mapper.TypeOfBookMapper;
import com.sti.utilitiesmodule.model.status.ModelStatus;
import com.sti.utilitiesmodule.repository.TypeOfBookRepository;
import com.sti.utilitiesmodule.service.TypeOfBookService;
import com.sti.utilitiesmodule.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for TypeOfBook entity.
 * @author Laurent G. Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class TypeOfBookServiceImpl implements TypeOfBookService {


    private final TypeOfBookRepository typeOfBookRepository;

    private final TypeOfBookMapper typeOfBookMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public TypeOfBookDto saveTypeOfBook(TypeOfBookDto typeOfBookDto) {
        TypeOfBook typeOfBook = TypeOfBook.buildTypeOfBookFromDto(this.typeOfBookMapper.dtoToTypeOfBook(typeOfBookDto));
        this.typeOfBookRepository.save(typeOfBook);
        return typeOfBookMapper.typeOfBookToDto(typeOfBook);
    }

    @Override
    public TypeOfBookDto findTypeOfBookById(String typeOfBookId) throws TypeOfBookNotFoundException {
        TypeOfBook typeOfBook = typeOfBookRepository.findById(typeOfBookId).orElseThrow(() -> TypeOfBookNotFoundException.buildTypeOfBookNotFoundExceptionForId(typeOfBookId));
        return typeOfBookMapper.typeOfBookToDto(isActiveTypeOfBook(typeOfBook,"typeOfBookId", typeOfBookId));
    }

    @Override
    public Page<TypeOfBookDto> findPaginatedSortedTypeOfBook(int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<TypeOfBookDto> typeOfBookDtos;
        typeOfBookDtos = typeOfBookMapper
                .typeOfBookToToDto(typeOfBookRepository.findAll(pageable).toList());

        return new PageImpl<>(typeOfBookDtos);
    }

    @Override
    public TypeOfBookDto updateTypeOfBook(TypeOfBookDto typeOfBookDto) {
        TypeOfBook typeOfBook = this.typeOfBookMapper.dtoToTypeOfBook(typeOfBookDto);
        this.typeOfBookRepository.save(typeOfBook);
        return typeOfBookMapper.typeOfBookToDto(typeOfBook);
    }

    @Override
    public void deleteTypeOfBook(String typeOfBookId) {
        TypeOfBook typeOfBook = typeOfBookMapper.dtoToTypeOfBook(findTypeOfBookById(typeOfBookId));
        typeOfBook.setTypeOfBookStatus(ModelStatus.INACTIVE);
        typeOfBookRepository.save(typeOfBook);
    }

    /**
     * Return typeOfBook if status code is ACTIVE.
     * @param typeOfBook TypeOfBook
     * @param queryField String
     * @param queryFieldValue String
     * @return TypeOfBook
     * @throws TypeOfBookNotFoundException ex
     */
    private TypeOfBook isActiveTypeOfBook(TypeOfBook typeOfBook, String queryField, String queryFieldValue){
        if(typeOfBook.getTypeOfBookStatus().getStatusCode() == 0){
            return typeOfBook;
        }
        throw TypeOfBookNotFoundException
                .buildTypeOfBookNotFoundExceptionForField(queryField, queryFieldValue);
    }
}

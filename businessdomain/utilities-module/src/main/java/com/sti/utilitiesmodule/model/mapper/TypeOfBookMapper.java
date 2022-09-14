package com.sti.utilitiesmodule.model.mapper;

import com.sti.utilitiesmodule.dto.TypeOfBookDto;
import com.sti.utilitiesmodule.model.TypeOfBook;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeOfBookMapper {


    TypeOfBook dtoToTypeOfBook(TypeOfBookDto dto);

    TypeOfBookDto typeOfBookToDto(TypeOfBook typeOfBook);

    List<TypeOfBook> dtoToTypeOfBook(List<TypeOfBookDto> dtos);

    List<TypeOfBookDto> typeOfBookToToDto(List<TypeOfBook> typeOfBooks);
}

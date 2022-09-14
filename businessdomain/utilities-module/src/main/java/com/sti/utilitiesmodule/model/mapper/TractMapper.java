package com.sti.utilitiesmodule.model.mapper;

import com.sti.utilitiesmodule.dto.TractDto;
import com.sti.utilitiesmodule.model.Tract;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TractMapper {

    Tract dtoToTract(TractDto dto);

    TractDto tractToDto(Tract tract);

    List<Tract> dtoToTract(List<TractDto> dtos);

    List<TractDto> tractToToDto(List<Tract> tracts);
}

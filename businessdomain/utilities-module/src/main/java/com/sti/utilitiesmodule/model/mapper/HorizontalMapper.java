package com.sti.utilitiesmodule.model.mapper;

import com.sti.utilitiesmodule.dto.HorizontalDto;
import com.sti.utilitiesmodule.model.Horizontal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorizontalMapper {

    Horizontal dtoToHorizontal(HorizontalDto dto);

    HorizontalDto horizontalToDto(Horizontal horizontal);

    List<Horizontal> dtoToHorizontal(List<HorizontalDto> dtos);

    List<HorizontalDto> horizontalToToDto(List<Horizontal> horizontals);
}

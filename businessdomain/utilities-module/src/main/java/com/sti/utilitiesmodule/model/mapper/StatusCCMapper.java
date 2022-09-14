package com.sti.utilitiesmodule.model.mapper;

import com.sti.utilitiesmodule.dto.StatusCCDto;
import com.sti.utilitiesmodule.model.StatusCC;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusCCMapper {

    StatusCC dtoToStatusCC(StatusCCDto dto);

    StatusCCDto statusCCToDto(StatusCC statusCC);

    List<StatusCC> dtoToStatusCC(List<StatusCCDto> dtos);

    List<StatusCCDto> statusCCToToDto(List<StatusCC> statusCCS);
}

package com.sti.securitymodule.model.mappers;

import com.sti.securitymodule.dto.RolDto;
import com.sti.securitymodule.model.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol dtoToRol(RolDto dto);

    RolDto rolToDto(Rol rol);

    List<Rol> dtoToRol(List<RolDto> dtos);

    List<RolDto> rolToDto(List<Rol> rols);
}

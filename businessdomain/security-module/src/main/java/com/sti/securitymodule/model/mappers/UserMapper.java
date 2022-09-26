package com.sti.securitymodule.model.mappers;

import com.sti.securitymodule.dto.UserDto;
import com.sti.securitymodule.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToUser(UserDto dto);

    UserDto userToDto(User user);

    List<User> dtoToUser(List<UserDto> dtos);

    List<UserDto> userToDto(List<User> users);
}

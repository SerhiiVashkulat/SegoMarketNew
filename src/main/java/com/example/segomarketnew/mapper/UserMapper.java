package com.example.segomarketnew.mapper;

import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    List<UserDto> toListUserDto(List<User> listUsers);
}
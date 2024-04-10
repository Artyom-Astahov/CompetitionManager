package by.artem.mapper.UserMapper;

import by.artem.dto.UserDto.UserCreateDto;
import by.artem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCreateMapper  {
    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

     User mapFrom(UserCreateDto userCreateDto);
}

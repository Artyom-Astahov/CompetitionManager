package by.artem.mapper.UserMapper;

import by.artem.dto.UserDto.UserReadDto;
import by.artem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserReadMapper {

    UserReadMapper INSTANCE = Mappers.getMapper(UserReadMapper.class);

    UserReadDto mapFrom(User user);

}

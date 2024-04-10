package by.artem.mapper.UserMapper;

import by.artem.dto.UserDto.UserUpdateDto;
import by.artem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserUpdateMapper {

    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    User mapFrom(UserUpdateDto userUpdateDto);
}

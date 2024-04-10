package by.artem.dto.UserDto;

import by.artem.entity.RolesEnum;
import by.artem.entity.UserInfo;

public record UserReadDto(
        Integer id,
        String login,
        String password,
        RolesEnum role,
        UserInfo userInfo) {
}

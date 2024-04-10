package by.artem.service;

import by.artem.dao.UserRepository;
import by.artem.dto.UserDto.UserCreateDto;
import by.artem.dto.UserDto.UserReadDto;
import by.artem.dto.UserDto.UserUpdateDto;
import by.artem.entity.RolesEnum;
import by.artem.entity.User;
import by.artem.mapper.UserMapper.UserCreateMapper;
import by.artem.mapper.UserMapper.UserReadMapper;
import by.artem.mapper.UserMapper.UserUpdateMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Integer create(UserCreateDto userCreateDto){
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(userCreateDto);
        if(!validationResult.isEmpty())
            throw new ConstraintViolationException(validationResult);

        var userEntity = UserCreateMapper.INSTANCE.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Integer id){
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id).map(UserReadMapper.INSTANCE::mapFrom);
    }

    public boolean update(UserUpdateDto userUpdateDto){
        User user = UserUpdateMapper.INSTANCE.mapFrom(userUpdateDto);
        var maybeUser = userRepository.findById(user.getId());
        userRepository.update(user);
        return maybeUser.isPresent();
    }

    public RolesEnum getRoleEnum(Integer id){
         return userRepository.findById(id).map(UserReadMapper.INSTANCE::mapFrom).get().role();
    }


}

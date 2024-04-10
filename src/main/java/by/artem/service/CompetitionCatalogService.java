package by.artem.service;

import by.artem.dao.CompetitionCatalogRepository;
import by.artem.dao.UserRepository;
import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogCreateDto;
import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogReadDto;
import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogUpdateDto;
import by.artem.dto.UserDto.UserCreateDto;
import by.artem.dto.UserDto.UserReadDto;
import by.artem.dto.UserDto.UserUpdateDto;
import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.mapper.CompetitionCatalogMapper.CompetitionCatalogCreateMapper;
import by.artem.mapper.CompetitionCatalogMapper.CompetitionCatalogReadMapper;
import by.artem.mapper.CompetitionCatalogMapper.CompetitionCatalogUpdateMapper;
import by.artem.mapper.UserMapper.UserCreateMapper;
import by.artem.mapper.UserMapper.UserReadMapper;
import by.artem.mapper.UserMapper.UserUpdateMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;
@RequiredArgsConstructor
public class CompetitionCatalogService {
    private final CompetitionCatalogRepository competitionCatalogRepository;

    public Integer create(CompetitionCatalogCreateDto competitionCatalogCreateDto){
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(competitionCatalogCreateDto);
        if(!validationResult.isEmpty())
            throw new ConstraintViolationException(validationResult);

        var competitionCatalogEntity = CompetitionCatalogCreateMapper.INSTANCE.mapFrom(competitionCatalogCreateDto);
        return competitionCatalogRepository.save(competitionCatalogEntity).getId();
    }

    public boolean delete(Integer id){
        var maybeCompetitionCatalog = competitionCatalogRepository.findById(id);
        maybeCompetitionCatalog.ifPresent(user -> competitionCatalogRepository.delete(id));
        return maybeCompetitionCatalog.isPresent();
    }

    public Optional<CompetitionCatalogReadDto> findById(Integer id) {
        return competitionCatalogRepository.findById(id).map(CompetitionCatalogReadMapper.INSTANCE::mapFrom);
    }

    public boolean update(CompetitionCatalogUpdateDto competitionCatalogUpdateDto){
        CompetitionCatalog competitionCatalog = CompetitionCatalogUpdateMapper.INSTANCE.mapFrom(competitionCatalogUpdateDto);
        var maybeCompetitionCatalog = competitionCatalogRepository.findById(competitionCatalog.getId());
        competitionCatalogRepository.update(competitionCatalog);
        return maybeCompetitionCatalog.isPresent();
    }
}

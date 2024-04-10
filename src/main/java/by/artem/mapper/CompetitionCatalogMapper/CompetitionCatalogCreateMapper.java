package by.artem.mapper.CompetitionCatalogMapper;

import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogCreateDto;
import by.artem.dto.UserDto.UserCreateDto;
import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.mapper.UserMapper.UserCreateMapper;
import org.mapstruct.factory.Mappers;

public interface CompetitionCatalogCreateMapper {

    CompetitionCatalogCreateMapper INSTANCE = Mappers.getMapper(CompetitionCatalogCreateMapper.class);

    CompetitionCatalog mapFrom(CompetitionCatalogCreateDto competitionCatalogCreateDto);
}

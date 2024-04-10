package by.artem.mapper.CompetitionCatalogMapper;

import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogCreateDto;
import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogReadDto;
import by.artem.dto.UserDto.UserReadDto;
import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.mapper.UserMapper.UserReadMapper;
import org.mapstruct.factory.Mappers;

public interface CompetitionCatalogReadMapper {

    CompetitionCatalogReadMapper INSTANCE = Mappers.getMapper(CompetitionCatalogReadMapper.class);

    CompetitionCatalogReadDto mapFrom(CompetitionCatalog competitionCatalog);
}

package by.artem.mapper.CompetitionCatalogMapper;

import by.artem.dto.CompetitionCatalogDto.CompetitionCatalogUpdateDto;
import by.artem.dto.UserDto.UserUpdateDto;
import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.mapper.UserMapper.UserUpdateMapper;
import org.mapstruct.factory.Mappers;

public interface CompetitionCatalogUpdateMapper {

    CompetitionCatalogUpdateMapper INSTANCE = Mappers.getMapper(CompetitionCatalogUpdateMapper.class);

    CompetitionCatalog mapFrom(CompetitionCatalogUpdateDto competitionCatalogUpdateDto);
}

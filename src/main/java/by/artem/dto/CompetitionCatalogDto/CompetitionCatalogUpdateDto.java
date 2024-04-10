package by.artem.dto.CompetitionCatalogDto;

import java.time.LocalDateTime;

public record CompetitionCatalogUpdateDto(Integer id,

                                          LocalDateTime dateEvent,
                                          String description) {
}

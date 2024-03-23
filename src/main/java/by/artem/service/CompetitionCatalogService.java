package by.artem.service;

import by.artem.dao.CompetitionCatalogDao;
import by.artem.dto.CompetitionCatalogDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionCatalogService {
    private static final CompetitionCatalogService INSTANCE = new CompetitionCatalogService();
    private final CompetitionCatalogDao competitionCatalogDao = CompetitionCatalogDao.getInstance();

    private CompetitionCatalogService() {
    }

    public static CompetitionCatalogService getInstance() {
        return INSTANCE;
    }

    public List<CompetitionCatalogDto> findAll() {
        return competitionCatalogDao.findAll().stream().map(competitionCatalog -> new CompetitionCatalogDto(
                competitionCatalog.getId(),
                competitionCatalog.getDateEvent(),
                competitionCatalog.getDescription()
        )).collect(Collectors.toList());
    }
}

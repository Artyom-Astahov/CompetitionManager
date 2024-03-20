package by.artem.dao;

import by.artem.dao.classes.CompetitionCatalog;

import java.util.List;
import java.util.Optional;

public class UsersDao implements Dao<Integer, CompetitionCatalog> {


    @Override
    public CompetitionCatalog create(CompetitionCatalog competitionCatalog) {
        return null;
    }

    @Override
    public boolean update(CompetitionCatalog competitionCatalog) {
        return false;
    }

    @Override
    public List<CompetitionCatalog> findAll() {
        return null;
    }

    @Override
    public Optional<CompetitionCatalog> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}

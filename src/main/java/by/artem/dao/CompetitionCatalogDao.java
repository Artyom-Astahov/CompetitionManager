package by.artem.dao;


import by.artem.dao.classes.CompetitionCatalog;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompetitionCatalogDao implements Dao<Integer, CompetitionCatalog>{

    private static final CompetitionCatalogDao INSTANCE = new CompetitionCatalogDao();

    private CompetitionCatalogDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.competition_catalog (date_event, description)
            VALUES (?, ?);
            """;
    private final String READ_ALL_SQL = """
                        select id, date_event, description from competition_manager_repo.public.competition_catalog
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where id = ?
            """;
    private final String UPDATE_SQL = """
                           update competition_manager_repo.public.competition_catalog
                            set date_event = ?, description = ? 
                            where id = ?;
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.competition_catalog where id = ?          
            """;

    public static CompetitionCatalogDao getInstance() {
        return INSTANCE;
    }

    @Override
    public CompetitionCatalog create(CompetitionCatalog competitionCatalog)  {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, (Date) competitionCatalog.getDateEvent());
            statement.setString(2, competitionCatalog.getDescription());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                competitionCatalog.setId(keys.getInt("id"));
            return competitionCatalog;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CompetitionCatalog competitionCatalog) {
        try (Connection connectionManager = ConnectionManager.get()) {
            var statement = connectionManager.prepareStatement(UPDATE_SQL);
            statement.setDate(1, (Date) competitionCatalog.getDateEvent());
            statement.setString(2, competitionCatalog.getDescription());
            statement.setInt(3, competitionCatalog.getId());
            var resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private CompetitionCatalog buildCompetitionCatalog(ResultSet result) throws SQLException {
        return new CompetitionCatalog(
                result.getInt("id"),
                result.getDate("date"),
                result.getString("description")

        );
    }

    @Override
    public List<CompetitionCatalog> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<CompetitionCatalog> competitioncatalogs = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                competitioncatalogs.add(buildCompetitionCatalog(result));
            return competitioncatalogs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CompetitionCatalog> findById(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_BY_ID_SQL);
            statement.setInt(1, integer);
            var result = statement.executeQuery();
            CompetitionCatalog competitioncatalog = null;
            if (result.next())
                competitioncatalog = buildCompetitionCatalog(result);

            return Optional.ofNullable(competitioncatalog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, integer);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

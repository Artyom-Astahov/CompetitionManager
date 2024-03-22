package by.artem.dao;

import by.artem.dao.classes.SportCategory;
import by.artem.dao.classes.SportCategoryEnum;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SportCategoryDao implements Dao<Integer, SportCategory> {

    private static final SportCategoryDao INSTANCE = new SportCategoryDao();

    private SportCategoryDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.sport_category (category)
            VALUES (?);
            """;
    private final String READ_ALL_SQL = """
                        select id, category from competition_manager_repo.public.sport_category
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where id = ?
            """;
    private final String UPDATE_SQL = """
                           update competition_manager_repo.public.sport_category
                            set category = ? 
                            where id = ?;
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.sport_category where id = ?          
            """;

    public static SportCategoryDao getInstance() {
        return INSTANCE;
    }

    @Override
    public SportCategory create(SportCategory sportCategory) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sportCategory.getCategory().toString());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                sportCategory.setId(keys.getInt("id"));
            return sportCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(SportCategory sportCategory) {
        try (Connection connectionManager = ConnectionManager.get()) {
            var statement = connectionManager.prepareStatement(UPDATE_SQL);
            statement.setString(1, sportCategory.getCategory().toString());
            statement.setInt(2, sportCategory.getId());
            var resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private SportCategory buildSportCategory(ResultSet result) throws SQLException {
        return new SportCategory(
                result.getInt("id"),
                SportCategoryEnum.valueOf(result.getString("category"))
        );
    }
    @Override
    public List<SportCategory> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<SportCategory> sportCategories = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                sportCategories.add(buildSportCategory(result));
            return sportCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SportCategory> findById(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_BY_ID_SQL);
            statement.setInt(1, integer);
            var result = statement.executeQuery();
            SportCategory sportCategory = null;
            if (result.next())
                sportCategory = buildSportCategory(result);

            return Optional.ofNullable(sportCategory);
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

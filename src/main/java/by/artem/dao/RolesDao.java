package by.artem.dao;

import by.artem.dao.classes.Roles;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RolesDao implements Dao<Integer, Roles> {

    private static final RolesDao INSTANCE = new RolesDao();

    private RolesDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.roles (role)
            VALUES (?);
            """;
    private final String READ_ALL_SQL = """
                        select id, role from competition_manager_repo.public.roles
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where id = ?
            """;
    private final String UPDATE_SQL = """
                           update competition_manager_repo.public.roles
                            set role = ? 
                            where id = ?;
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.roles where id = ?          
            """;

    public static RolesDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Roles create(Roles role) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, role.getRole());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                role.setId(keys.getInt("id"));
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Roles role) {
        try (Connection connectionManager = ConnectionManager.get()) {
            var statement = connectionManager.prepareStatement(UPDATE_SQL);
            statement.setString(1, role.getRole());
            statement.setInt(2, role.getId());
            var resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Roles buildRole(ResultSet result) throws SQLException {
        return new Roles(
                result.getInt("id"),
                result.getString("role")
        );
    }
    @Override
    public List<Roles> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<Roles> roles = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                roles.add(buildRole(result));
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Roles> findById(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_BY_ID_SQL);
            statement.setInt(1, integer);
            var result = statement.executeQuery();
            Roles role = null;
            if (result.next())
                role = buildRole(result);

            return Optional.ofNullable(role);
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

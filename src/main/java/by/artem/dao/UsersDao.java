package by.artem.dao;

import by.artem.dao.classes.Users;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDao implements Dao<Integer, Users> {

    private static final UsersDao INSTANCE = new UsersDao();

    private UsersDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.users (login, password, role_id)
            VALUES (?, ?, ?);
            """;
    private final String READ_ALL_SQL = """
                        select id, login, password, role_id from competition_manager_repo.public.users
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where id = ?
            """;
    private final String UPDATE_SQL = """
                           update competition_manager_repo.public.users
                            set login = ?, password = ?, role_id = ?
                            where id = ?;
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.users where id = ?          
            """;

    public static UsersDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Users create(Users user) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                user.setId(keys.getInt("id"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Users user) {
        try (Connection connectionManager = ConnectionManager.get()) {
            var statement = connectionManager.prepareStatement(UPDATE_SQL);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.setInt(4, user.getId());
            var resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Users buildUser(ResultSet result) throws SQLException {
        return new Users(
                result.getInt("id"),
                result.getString("login"),
                result.getString("password"),
                result.getInt("role_id")
        );
    }

    @Override
    public List<Users> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<Users> users = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                users.add(buildUser(result));
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Users> findById(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_BY_ID_SQL);
            statement.setInt(1, integer);
            var result = statement.executeQuery();
            Users user = null;
            if (result.next())
                user = buildUser(result);

            return Optional.ofNullable(user);
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

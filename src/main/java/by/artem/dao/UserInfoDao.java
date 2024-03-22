package by.artem.dao;

import by.artem.dao.classes.UserInfo;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInfoDao implements Dao<Integer, UserInfo> {

    private static final UserInfoDao INSTANCE = new UserInfoDao();

    private UserInfoDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.user_info (user_id, name, weight, category_id, date_birth)
            VALUES (?, ?, ?, ?, ?);
            """;
    private final String READ_ALL_SQL = """
                        select id, user_id, name, weight, category_id, date_birth from competition_manager_repo.public.user_info
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where id = ?
            """;
    private final String UPDATE_SQL = """
                           update competition_manager_repo.public.user_info
                            set user_id = ?, name = ?, weight = ?, category_id = ?, date_birth = ? 
                            where id = ?;
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.user_info where id = ?          
            """;

    public static UserInfoDao getInstance() {
        return INSTANCE;
    }

    @Override
    public UserInfo create(UserInfo userInfo) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userInfo.getUserId());
            statement.setString(2, userInfo.getName());
            statement.setInt(3, userInfo.getWeight());
            statement.setInt(4, userInfo.getCategoryId());
            statement.setDate(5, (Date) userInfo.getDateBirth());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                userInfo.setId(keys.getInt("id"));
            return userInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserInfo userInfo) {
        try (Connection connectionManager = ConnectionManager.get()) {
            var statement = connectionManager.prepareStatement(UPDATE_SQL);
            statement.setInt(1, userInfo.getUserId());
            statement.setString(2, userInfo.getName());
            statement.setInt(3, userInfo.getWeight());
            statement.setInt(4, userInfo.getCategoryId());
            statement.setDate(5, (Date) userInfo.getDateBirth());
            statement.setInt(6, userInfo.getId());
            var resultSet = statement.executeUpdate();
            return resultSet > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserInfo buildUserInfo(ResultSet result) throws SQLException {
        return new UserInfo(
                result.getInt("id"),
                result.getInt("user_id"),
                result.getString("name"),
                result.getInt("weight"),
                result.getInt("category_id"),
                result.getDate("date_birth")
        );
    }

    @Override
    public List<UserInfo> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<UserInfo> userInfos = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                userInfos.add(buildUserInfo(result));
            return userInfos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<UserInfo> findById(Integer integer) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_BY_ID_SQL);
            statement.setInt(1, integer);
            var result = statement.executeQuery();
            UserInfo userInfo = null;
            if (result.next())
                userInfo = buildUserInfo(result);

            return Optional.ofNullable(userInfo);
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

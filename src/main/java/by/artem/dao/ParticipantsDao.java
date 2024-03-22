package by.artem.dao;

import by.artem.dao.classes.Participants;
import by.artem.dao.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantsDao implements Dao<Integer, Participants> {

    private static final Participants INSTANCE = new Participants();

    private ParticipantsDao() {
    }

    private final String CREATE_SQL = """
            insert into competition_manager_repo.public.participants (user_id, competition_catalog_id)
            VALUES (?, ?);
            """;
    private final String READ_ALL_SQL = """
                        select user_id, competition_catalog_id from competition_manager_repo.public.participants
            """;
    private final String READ_BY_ID_SQL = READ_ALL_SQL + """
                        where user_id = ? and competition_catalog_id = ?
            """;
    private final String DELETE_SQL = """
                   delete from competition_manager_repo.public.participants where user_id = ?         
            """;

    public static Participants getInstance() {
        return INSTANCE;
    }

    @Override
    public Participants create(Participants participant) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(CREATE_SQL);
            statement.setInt(1, participant.getUserId());
            statement.setInt(2, participant.getCompetitionCatalogId());
            statement.executeUpdate();
            return participant;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Participants participant) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    private Participants buildParticipant(ResultSet result) throws SQLException {
        return new Participants(
                result.getInt("user_id"),
                result.getInt("competition_catalog_id")
        );
    }

    @Override
    public List<Participants> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(READ_ALL_SQL);
            List<Participants> participants = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                participants.add(buildParticipant(result));
            return participants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Participants> findById(Integer integer) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean delete(Integer userId) {
        try (Connection connection = ConnectionManager.get()) {
            var statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

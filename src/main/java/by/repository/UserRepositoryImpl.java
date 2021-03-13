package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String IN_TO_CHAT_PARTICIPATES = "INSERT INTO simple.public.users VALUES (DEFAULT, ?, DEFAULT) ";

    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public UserDAOResponse add(UserDAORequest image) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(IN_TO_CHAT_PARTICIPATES);
//            preparedStatement.setInt(1, chatParticipation.getUserID());
//            preparedStatement.setInt(2, chatParticipation.getChatID());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDAOResponse delete(UserDAORequest image) {
        return null;
    }

    @Override
    public List<UserDAOResponse> deleteAll() {
        return null;
    }

    @Override
    public UserDAOResponse get(UserDAORequest image) {
        return null;
    }

    @Override
    public List<UserDAOResponse> getAll() {
        return null;
    }

    @Override
    public List<UserDAOResponse> set(UserDAORequest image) {
        return null;
    }
}

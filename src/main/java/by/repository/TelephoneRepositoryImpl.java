package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TelephoneRepositoryImpl implements TelephoneRepository {

    private static final String IN_TO_CHAT_PARTICIPATES = "INSERT INTO simple.public.telephones VALUES (DEFAULT, ?, DEFAULT) ";

    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public TelephoneDAOResponse add(TelephoneDAORequest image) {
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
    public TelephoneDAOResponse delete(TelephoneDAORequest image) {
        return null;
    }

    @Override
    public List<TelephoneDAOResponse> deleteAll() {
        return null;
    }

    @Override
    public TelephoneDAOResponse get(TelephoneDAORequest image) {
        return null;
    }

    @Override
    public List<TelephoneDAOResponse> getAll() {
        return null;
    }

    @Override
    public List<TelephoneDAOResponse> set(TelephoneDAORequest image) {
        return null;
    }
}

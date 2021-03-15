package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.util.TextLabels.ADD_TO_USERS;
import static by.util.TextLabels.DELETE_ALL_USERS;
import static by.util.TextLabels.DELETE_USER;
import static by.util.TextLabels.EMAIL;
import static by.util.TextLabels.FIRST_NAME;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.LAST_NAME;
import static by.util.TextLabels.SELECT_ALL_USERS;
import static by.util.TextLabels.SELECT_USER_BY_EMAIL;
import static by.util.TextLabels.SELECT_USER_BY_ID;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection = ConnectionFactory.getConnection();

    @Override
    public UserDAOResponse add(UserDAORequest userDAORequest) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_USERS);
            preparedStatement.setString(1, userDAORequest.getLastName());
            preparedStatement.setString(2, userDAORequest.getFirstName());
            preparedStatement.setString(3, userDAORequest.getEmail());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(userDAORequest);
    }

    @Override
    public UserDAOResponse delete(UserDAORequest userDAORequest) {
        UserDAOResponse userDAOResponse = get(userDAORequest);
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, userDAORequest.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDAOResponse;
    }

    @Override
    public List<UserDAOResponse> deleteAll() {
        List<UserDAOResponse> userDAOResponses = getAll();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_USERS);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDAOResponses;
    }

    @Override
    public UserDAOResponse get(UserDAORequest userDAORequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            statement.setString(1, userDAORequest.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDAOResponse userDAOResponse = new UserDAOResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDAOResponse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDAOResponse get(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDAOResponse userDAOResponse = new UserDAOResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDAOResponse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDAOResponse> getAll() {
        List<UserDAOResponse> userDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDAOResponse userDAOResponse = new UserDAOResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                userDAOResponses.add(userDAOResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDAOResponses;
    }
}

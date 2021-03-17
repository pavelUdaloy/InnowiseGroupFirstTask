package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private Connection connection;

    @Override
    public UserDaoResponse add(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_USERS)) {
            preparedStatement.setString(1, userDaoRequest.getLastName());
            preparedStatement.setString(2, userDaoRequest.getFirstName());
            preparedStatement.setString(3, userDaoRequest.getEmail());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(userDaoRequest);
    }

    @Override
    public UserDaoResponse delete(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        UserDaoResponse userDaoResponse = get(userDaoRequest);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1, userDaoRequest.getEmail());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return userDaoResponse;
    }

    @Override
    public List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<UserDaoResponse> userDaoResponses = getAll();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_USERS);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return userDaoResponses;
    }

    @Override
    public UserDaoResponse get(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            statement.setString(1, userDaoRequest.getEmail());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                UserDaoResponse userDaoResponse = new UserDaoResponse();
                userDaoResponse.setId(resultSet.getInt(ID));
                userDaoResponse.setEmail(resultSet.getString(EMAIL));
                userDaoResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDaoResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDaoResponse;
            } else {
                throw new NullQueryException();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                UserDaoResponse userDaoResponse = new UserDaoResponse();
                userDaoResponse.setId(resultSet.getInt(ID));
                userDaoResponse.setEmail(resultSet.getString(EMAIL));
                userDaoResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDaoResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDaoResponse;
            } else {
                throw new NullQueryException();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<UserDaoResponse> userDaoResponses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            connection.commit();
            while (resultSet.next()) {
                UserDaoResponse userDaoResponse = new UserDaoResponse();
                userDaoResponse.setId(resultSet.getInt(ID));
                userDaoResponse.setEmail(resultSet.getString(EMAIL));
                userDaoResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDaoResponse.setLastName(resultSet.getString(LAST_NAME));
                userDaoResponses.add(userDaoResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (userDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return userDaoResponses;
    }
}

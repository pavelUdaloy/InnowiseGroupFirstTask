package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

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
    public UserDaoResponse add(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_USERS)) {
            preparedStatement.setString(1, userDAORequest.getLastName());
            preparedStatement.setString(2, userDAORequest.getFirstName());
            preparedStatement.setString(3, userDAORequest.getEmail());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(userDAORequest);
    }

    @Override
    public UserDaoResponse delete(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        UserDaoResponse userDAOResponse = get(userDAORequest);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1, userDAORequest.getEmail());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return userDAOResponse;
    }

    @Override
    public List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<UserDaoResponse> userDaoRespons = getAll();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_USERS);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return userDaoRespons;
    }

    @Override
    public UserDaoResponse get(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            statement.setString(1, userDAORequest.getEmail());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                UserDaoResponse userDAOResponse = new UserDaoResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDAOResponse;
            } else return null;
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                UserDaoResponse userDAOResponse = new UserDaoResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                return userDAOResponse;
            } else return null;
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<UserDaoResponse> userDaoRespons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            connection.commit();
            while (resultSet.next()) {
                UserDaoResponse userDAOResponse = new UserDaoResponse();
                userDAOResponse.setId(resultSet.getInt(ID));
                userDAOResponse.setEmail(resultSet.getString(EMAIL));
                userDAOResponse.setFirstName(resultSet.getString(FIRST_NAME));
                userDAOResponse.setLastName(resultSet.getString(LAST_NAME));
                userDaoRespons.add(userDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return userDaoRespons;
    }
}

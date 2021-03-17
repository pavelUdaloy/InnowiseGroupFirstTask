package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
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

import static by.util.TextLabels.ADD_TO_TELEPHONES;
import static by.util.TextLabels.DELETE_ALL_TELEPHONES;
import static by.util.TextLabels.DELETE_TELEPHONE;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.NUMBER;
import static by.util.TextLabels.OWNER_ID;
import static by.util.TextLabels.SELECT_ALL_TELEPHONES;
import static by.util.TextLabels.SELECT_TELEPHONE;
import static by.util.TextLabels.SELECT_TELEPHONE_BY_OWNER_ID;
import static by.util.TextLabels.UPDATE_TELEPHONE_BY_ID;

public class TelephoneRepositoryImpl implements TelephoneRepository {

    private Connection connection;

    @Override
    public List<TelephoneDaoResponse> addAll(List<TelephoneDaoRequest> telephoneDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<TelephoneDaoResponse> telephoneDaoResponses = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        for (TelephoneDaoRequest telephoneDaoRequest : telephoneDaoRequests) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_TELEPHONES)) {
                preparedStatement.setString(1, telephoneDaoRequest.getNumber());
                preparedStatement.setInt(2, telephoneDaoRequest.getOwnerId());
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                throw new IncorrectSQLParametersException(e);
            }
            telephoneDaoResponses.add(get(telephoneDaoRequest));
        }
        return telephoneDaoResponses;
    }

    @Override
    public TelephoneDaoResponse delete(TelephoneDaoRequest telephoneDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        TelephoneDaoResponse telephoneDaoResponse = get(telephoneDaoRequest);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TELEPHONE)) {
            statement.setInt(1, telephoneDaoRequest.getOwnerId());
            statement.setString(2, telephoneDaoRequest.getNumber());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDaoResponse;
    }

    @Override
    public List<TelephoneDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoResponses = getAll();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_TELEPHONES);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDaoResponses;
    }

    @Override
    public TelephoneDaoResponse get(TelephoneDaoRequest telephoneDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE)) {
            statement.setInt(1, telephoneDaoRequest.getOwnerId());
            statement.setString(2, telephoneDaoRequest.getNumber());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                TelephoneDaoResponse telephoneDaoResponse = new TelephoneDaoResponse();
                telephoneDaoResponse.setId(resultSet.getInt(ID));
                telephoneDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDaoResponse.setNumber(resultSet.getString(NUMBER));
                return telephoneDaoResponse;
            } else {
                throw new NullQueryException();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<TelephoneDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoResponses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE_BY_OWNER_ID)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                TelephoneDaoResponse telephoneDAOResponse = new TelephoneDaoResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDaoResponses.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (telephoneDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return telephoneDaoResponses;
    }

    @Override
    public List<TelephoneDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoResponses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_TELEPHONES);
            connection.commit();
            while (resultSet.next()) {
                TelephoneDaoResponse telephoneDAOResponse = new TelephoneDaoResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDaoResponses.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (telephoneDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return telephoneDaoResponses;
    }

    @Override
    public TelephoneDaoResponse update(TelephoneDaoRequest telephoneDaoRequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        TelephoneDaoResponse telephoneDaoResponse = get(telephoneDaoRequest);
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TELEPHONE_BY_ID)) {
            statement.setString(1, newNumber);
            statement.setInt(2, telephoneDaoResponse.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException troubles) {
            throw new IncorrectSQLParametersException(troubles);
        }
        telephoneDaoRequest.setNumber(newNumber);
        return get(telephoneDaoRequest);
    }
}

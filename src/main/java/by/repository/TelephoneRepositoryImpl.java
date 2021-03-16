package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

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
    public List<TelephoneDaoResponse> addAll(List<TelephoneDaoRequest> telephoneDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        for (TelephoneDaoRequest telephoneDAORequest : telephoneDaoRequests) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_TELEPHONES)) {
                preparedStatement.setString(1, telephoneDAORequest.getNumber());
                preparedStatement.setInt(2, telephoneDAORequest.getOwnerId());
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                throw new IncorrectSQLParametersException(e);
            }
            telephoneDaoRespons.add(get(telephoneDAORequest));
        }
        return telephoneDaoRespons;
    }

    @Override
    public TelephoneDaoResponse delete(TelephoneDaoRequest telephoneDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        TelephoneDaoResponse telephoneDAOResponse = get(telephoneDAORequest);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TELEPHONE)) {
            statement.setInt(1, telephoneDAORequest.getOwnerId());
            statement.setString(2, telephoneDAORequest.getNumber());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDAOResponse;
    }

    @Override
    public List<TelephoneDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoRespons = getAll();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_TELEPHONES);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDaoRespons;
    }

    @Override
    public TelephoneDaoResponse get(TelephoneDaoRequest telephoneDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE)) {
            statement.setInt(1, telephoneDAORequest.getOwnerId());
            statement.setString(2, telephoneDAORequest.getNumber());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                TelephoneDaoResponse telephoneDAOResponse = new TelephoneDaoResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                return telephoneDAOResponse;
            } else return null;
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<TelephoneDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoRespons = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE_BY_OWNER_ID)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                TelephoneDaoResponse telephoneDAOResponse = new TelephoneDaoResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDaoRespons.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDaoRespons;
    }

    @Override
    public List<TelephoneDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<TelephoneDaoResponse> telephoneDaoRespons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_TELEPHONES);
            connection.commit();
            while (resultSet.next()) {
                TelephoneDaoResponse telephoneDAOResponse = new TelephoneDaoResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDaoRespons.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return telephoneDaoRespons;
    }

    @Override
    public TelephoneDaoResponse update(TelephoneDaoRequest telephoneDAORequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        TelephoneDaoResponse telephoneDAOResponse = get(telephoneDAORequest);
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TELEPHONE_BY_ID)) {
            statement.setString(1, newNumber);
            statement.setInt(2, telephoneDAOResponse.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException troubles) {
            throw new IncorrectSQLParametersException(troubles);
        }
        telephoneDAORequest.setNumber(newNumber);
        return get(telephoneDAORequest);
    }
}

package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private final Connection connection = ConnectionFactory.getConnection();

    @Override
    public List<TelephoneDAOResponse> addAll(List<TelephoneDAORequest> telephoneDAORequests) {
        List<TelephoneDAOResponse> telephoneDAOResponses = new ArrayList<>();
        for (TelephoneDAORequest telephoneDAORequest : telephoneDAORequests) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_TELEPHONES);
                preparedStatement.setString(1, telephoneDAORequest.getNumber());
                preparedStatement.setInt(2, telephoneDAORequest.getOwnerId());
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            telephoneDAOResponses.add(get(telephoneDAORequest));
        }
        return telephoneDAOResponses;
    }

    @Override
    public TelephoneDAOResponse delete(TelephoneDAORequest telephoneDAORequest) {
        TelephoneDAOResponse telephoneDAOResponse = get(telephoneDAORequest);
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_TELEPHONE);
            statement.setInt(1, telephoneDAORequest.getOwnerId());
            statement.setString(2, telephoneDAORequest.getNumber());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneDAOResponse;
    }

    @Override
    public List<TelephoneDAOResponse> deleteAll() {
        List<TelephoneDAOResponse> telephoneDAOResponses = getAll();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_TELEPHONES);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneDAOResponses;
    }

    @Override
    public TelephoneDAOResponse get(TelephoneDAORequest telephoneDAORequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE);
            statement.setInt(1, telephoneDAORequest.getOwnerId());
            statement.setString(2, telephoneDAORequest.getNumber());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TelephoneDAOResponse telephoneDAOResponse = new TelephoneDAOResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                return telephoneDAOResponse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TelephoneDAOResponse> getByOwnerId(Integer ownerId) {
        List<TelephoneDAOResponse> telephoneDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_TELEPHONE_BY_OWNER_ID);
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TelephoneDAOResponse telephoneDAOResponse = new TelephoneDAOResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDAOResponses.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneDAOResponses;
    }

    @Override
    public List<TelephoneDAOResponse> getAll() {
        List<TelephoneDAOResponse> telephoneDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TELEPHONES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TelephoneDAOResponse telephoneDAOResponse = new TelephoneDAOResponse();
                telephoneDAOResponse.setId(resultSet.getInt(ID));
                telephoneDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                telephoneDAOResponse.setNumber(resultSet.getString(NUMBER));
                telephoneDAOResponses.add(telephoneDAOResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneDAOResponses;
    }

    @Override
    public TelephoneDAOResponse set(TelephoneDAORequest telephoneDAORequest, String newNumber) {
        TelephoneDAOResponse telephoneDAOResponse = get(telephoneDAORequest);
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TELEPHONE_BY_ID);
            statement.setString(1, newNumber);
            statement.setInt(2, telephoneDAOResponse.getId());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        telephoneDAORequest.setNumber(newNumber);
        return get(telephoneDAORequest);
    }
}

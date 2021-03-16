package by.repository;

import by.db.ConnectionFactory;
import by.entity.abstractive.AbstractCarAd;
import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.util.TextLabels.ADD_TO_CAR_ADS;
import static by.util.TextLabels.AGE;
import static by.util.TextLabels.BRAND;
import static by.util.TextLabels.CONDITION;
import static by.util.TextLabels.CREATION_DATE;
import static by.util.TextLabels.DELETE_ALL_CAR_ADS;
import static by.util.TextLabels.DELETE_CAR_AD;
import static by.util.TextLabels.ENGINE;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.LAST_EDIT_DATE;
import static by.util.TextLabels.MILEAGE;
import static by.util.TextLabels.MODEL;
import static by.util.TextLabels.OWNER_ID;
import static by.util.TextLabels.POWER;
import static by.util.TextLabels.SELECT_ALL_CAR_ADS;
import static by.util.TextLabels.SELECT_CAR_AD;
import static by.util.TextLabels.SELECT_CAR_AD_BY_ID;
import static by.util.TextLabels.SELECT_WITH_PAGINATION;
import static by.util.TextLabels.UPDATE_CAR_AD_AGE_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_BRAND_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_ENGINE_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_LAST_EDIT_DATE_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_MILEAGE_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_MODEL_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_POWER_BY_ID;

public class CarAdRepositoryImpl implements CarAdRepository {

    private Connection connection;

    @Override
    public CarAdDaoResponse add(CarAdDaoRequest carAdDAORequest) throws ConnectionWithDBLostException, IncorrectSQLParametersException {
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_CAR_ADS);
            preparedStatement.setInt(1, carAdDAORequest.getAge());
            preparedStatement.setString(2, carAdDAORequest.getBrand());
            preparedStatement.setString(3, carAdDAORequest.getModel());
            preparedStatement.setString(4, carAdDAORequest.getCondition().toString());
            preparedStatement.setInt(5, carAdDAORequest.getMileage());
            preparedStatement.setInt(6, carAdDAORequest.getEngineSize());
            preparedStatement.setInt(7, carAdDAORequest.getEnginePower());
            preparedStatement.setInt(8, carAdDAORequest.getOwnerId());
            preparedStatement.setTimestamp(9, carAdDAORequest.getCreationDate());
            preparedStatement.setTimestamp(10, carAdDAORequest.getLastEditDate());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(carAdDAORequest);
    }

    @Override
    public CarAdDaoResponse delete(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = get(carAdDAORequest);
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_AD);
            statement.setInt(1, carAdDAOResponse.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDAOResponse;
    }

    @Override
    public CarAdDaoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = get(id);
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_AD);
            statement.setInt(1, id);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDAOResponse;
    }

    @Override
    public List<CarAdDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = getAll();
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CAR_ADS);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDAORespons;
    }

    @Override
    public CarAdDaoResponse get(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD);
            statement.setInt(1, carAdDAORequest.getOwnerId());
            statement.setTimestamp(2, carAdDAORequest.getCreationDate());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                return getCarAdDAOResponse(resultSet);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return null;
    }

    @Override
    public CarAdDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                return getCarAdDAOResponse(resultSet);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return null;
    }

    @Override
    public List<CarAdDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<CarAdDaoResponse> carAdDAORespons = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CAR_ADS);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                CarAdDaoResponse carAdDAOResponse = getCarAdDAOResponse(resultSet);
                carAdDAORespons.add(carAdDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDAORespons;
    }

    @Override
    public CarAdDaoResponse update(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_AD_AGE_BY_ID);
            statement.setInt(1, carAdDAORequest.getAge());
            statement.setInt(2, carAdDAORequest.getId());
            statement.execute();
            PreparedStatement statement2 = connection.prepareStatement(UPDATE_CAR_AD_BRAND_BY_ID);
            statement2.setString(1, carAdDAORequest.getBrand());
            statement2.setInt(2, carAdDAORequest.getId());
            statement2.execute();
            PreparedStatement statement3 = connection.prepareStatement(UPDATE_CAR_AD_MODEL_BY_ID);
            statement3.setString(1, carAdDAORequest.getModel());
            statement3.setInt(2, carAdDAORequest.getId());
            statement3.execute();
            PreparedStatement statement4 = connection.prepareStatement(UPDATE_CAR_AD_ENGINE_BY_ID);
            statement4.setInt(1, carAdDAORequest.getEngineSize());
            statement4.setInt(2, carAdDAORequest.getId());
            statement4.execute();
            PreparedStatement statement5 = connection.prepareStatement(UPDATE_CAR_AD_POWER_BY_ID);
            statement5.setInt(1, carAdDAORequest.getEnginePower());
            statement5.setInt(2, carAdDAORequest.getId());
            statement5.execute();
            PreparedStatement statement6 = connection.prepareStatement(UPDATE_CAR_AD_MILEAGE_BY_ID);
            statement6.setInt(1, carAdDAORequest.getMileage());
            statement6.setInt(2, carAdDAORequest.getId());
            statement6.execute();
            PreparedStatement statement7 = connection.prepareStatement(UPDATE_CAR_AD_LAST_EDIT_DATE_BY_ID);
            statement7.setTimestamp(1, carAdDAORequest.getLastEditDate());
            statement7.setInt(2, carAdDAORequest.getId());
            statement7.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(carAdDAORequest.getId());
    }

    @Override
    public List<CarAdDaoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<CarAdDaoResponse> carAdDAORespons = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_WITH_PAGINATION);
            int offset = (page - 1) * size;
            statement.setInt(1, offset);
            statement.setInt(2, size);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                CarAdDaoResponse carAdDAOResponse = getCarAdDAOResponse(resultSet);
                carAdDAORespons.add(carAdDAOResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDAORespons;
    }

    private CarAdDaoResponse getCarAdDAOResponse(ResultSet resultSet) throws SQLException {
        CarAdDaoResponse carAdDAOResponse = new CarAdDaoResponse();
        carAdDAOResponse.setId(resultSet.getInt(ID));
        carAdDAOResponse.setAge(resultSet.getInt(AGE));
        carAdDAOResponse.setBrand(resultSet.getString(BRAND));
        carAdDAOResponse.setModel(resultSet.getString(MODEL));
        carAdDAOResponse.setCondition(AbstractCarAd.CarCondition.valueOf(resultSet.getString(CONDITION)));
        carAdDAOResponse.setMileage(resultSet.getInt(MILEAGE));
        carAdDAOResponse.setEngineSize(resultSet.getInt(ENGINE));
        carAdDAOResponse.setEnginePower(resultSet.getInt(POWER));
        carAdDAOResponse.setOwnerId(resultSet.getInt(OWNER_ID));
        carAdDAOResponse.setCreationDate(resultSet.getTimestamp(CREATION_DATE));
        carAdDAOResponse.setLastEditDate(resultSet.getTimestamp(LAST_EDIT_DATE));
        return carAdDAOResponse;
    }
}

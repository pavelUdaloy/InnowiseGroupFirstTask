package by.repository;

import by.db.ConnectionFactory;
import by.entity.abstractive.AbstractCarAd;
import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
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
    public CarAdDaoResponse add(CarAdDaoRequest carAdDaoRequest) throws ConnectionWithDBLostException, IncorrectSQLParametersException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_CAR_ADS)) {
            preparedStatement.setInt(1, carAdDaoRequest.getAge());
            preparedStatement.setString(2, carAdDaoRequest.getBrand());
            preparedStatement.setString(3, carAdDaoRequest.getModel());
            preparedStatement.setString(4, carAdDaoRequest.getCondition().toString());
            preparedStatement.setInt(5, carAdDaoRequest.getMileage());
            preparedStatement.setInt(6, carAdDaoRequest.getEngineSize());
            preparedStatement.setInt(7, carAdDaoRequest.getEnginePower());
            preparedStatement.setInt(8, carAdDaoRequest.getOwnerId());
            preparedStatement.setTimestamp(9, carAdDaoRequest.getCreationDate());
            preparedStatement.setTimestamp(10, carAdDaoRequest.getLastEditDate());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException();
        }
        return get(carAdDaoRequest);
    }

    @Override
    public CarAdDaoResponse delete(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoResponse carAdDaoResponse = get(carAdDaoRequest);
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CAR_AD)) {
            statement.setInt(1, carAdDaoResponse.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDaoResponse;
    }

    @Override
    public CarAdDaoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoResponse carAdDaoResponse = get(id);
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CAR_AD)) {
            statement.setInt(1, id);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDaoResponse;
    }

    @Override
    public List<CarAdDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<CarAdDaoResponse> carAdDaoResponses = getAll();
        connection = ConnectionFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_CAR_ADS);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return carAdDaoResponses;
    }

    @Override
    public CarAdDaoResponse get(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD)) {
            statement.setInt(1, carAdDaoRequest.getOwnerId());
            statement.setTimestamp(2, carAdDaoRequest.getCreationDate());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                return getCarAdDaoResponse(resultSet);
            } else throw new NullQueryException();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public CarAdDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                return getCarAdDaoResponse(resultSet);
            } else throw new NullQueryException();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<CarAdDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<CarAdDaoResponse> carAdDaoResponses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CAR_ADS);
            connection.commit();
            while (resultSet.next()) {
                CarAdDaoResponse carAdDaoResponse = getCarAdDaoResponse(resultSet);
                carAdDaoResponses.add(carAdDaoResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (carAdDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return carAdDaoResponses;
    }

    @Override
    public CarAdDaoResponse update(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_AD_AGE_BY_ID);
             PreparedStatement statement2 = connection.prepareStatement(UPDATE_CAR_AD_BRAND_BY_ID);
             PreparedStatement statement3 = connection.prepareStatement(UPDATE_CAR_AD_MODEL_BY_ID);
             PreparedStatement statement4 = connection.prepareStatement(UPDATE_CAR_AD_ENGINE_BY_ID);
             PreparedStatement statement5 = connection.prepareStatement(UPDATE_CAR_AD_POWER_BY_ID);
             PreparedStatement statement6 = connection.prepareStatement(UPDATE_CAR_AD_LAST_EDIT_DATE_BY_ID);
             PreparedStatement statement7 = connection.prepareStatement(UPDATE_CAR_AD_MILEAGE_BY_ID)) {
            statement.setInt(1, carAdDaoRequest.getAge());
            statement.setInt(2, carAdDaoRequest.getId());
            statement.execute();
            statement2.setString(1, carAdDaoRequest.getBrand());
            statement2.setInt(2, carAdDaoRequest.getId());
            statement2.execute();
            statement3.setString(1, carAdDaoRequest.getModel());
            statement3.setInt(2, carAdDaoRequest.getId());
            statement3.execute();
            statement4.setInt(1, carAdDaoRequest.getEngineSize());
            statement4.setInt(2, carAdDaoRequest.getId());
            statement4.execute();
            statement5.setInt(1, carAdDaoRequest.getEnginePower());
            statement5.setInt(2, carAdDaoRequest.getId());
            statement5.execute();
            statement6.setTimestamp(1, carAdDaoRequest.getLastEditDate());
            statement6.setInt(2, carAdDaoRequest.getId());
            statement6.execute();
            statement7.setInt(1, carAdDaoRequest.getMileage());
            statement7.setInt(2, carAdDaoRequest.getId());
            statement7.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(carAdDaoRequest.getId());
    }

    @Override
    public List<CarAdDaoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<CarAdDaoResponse> carAdDaoResponses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_PAGINATION)) {
            int offset = (page - 1) * size;
            statement.setInt(1, offset);
            statement.setInt(2, size);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                CarAdDaoResponse carAdDaoResponse = getCarAdDaoResponse(resultSet);
                carAdDaoResponses.add(carAdDaoResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (carAdDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return carAdDaoResponses;
    }

    private CarAdDaoResponse getCarAdDaoResponse(ResultSet resultSet) throws IncorrectSQLParametersException {
        try {
            CarAdDaoResponse carAdDaoResponse = new CarAdDaoResponse();
            carAdDaoResponse.setId(resultSet.getInt(ID));
            carAdDaoResponse.setAge(resultSet.getInt(AGE));
            carAdDaoResponse.setBrand(resultSet.getString(BRAND));
            carAdDaoResponse.setModel(resultSet.getString(MODEL));
            carAdDaoResponse.setCondition(AbstractCarAd.CarCondition.valueOf(resultSet.getString(CONDITION)));
            carAdDaoResponse.setMileage(resultSet.getInt(MILEAGE));
            carAdDaoResponse.setEngineSize(resultSet.getInt(ENGINE));
            carAdDaoResponse.setEnginePower(resultSet.getInt(POWER));
            carAdDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
            carAdDaoResponse.setCreationDate(resultSet.getTimestamp(CREATION_DATE));
            carAdDaoResponse.setLastEditDate(resultSet.getTimestamp(LAST_EDIT_DATE));
            return carAdDaoResponse;
        } catch (SQLException ex) {
            throw new IncorrectSQLParametersException();
        }
    }
}

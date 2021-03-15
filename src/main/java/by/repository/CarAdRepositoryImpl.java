package by.repository;

import by.db.ConnectionFactory;
import by.entity.abstractive.AbstractCarAd;
import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;

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
import static by.util.TextLabels.UPDATE_CAR_AD_MILEAGE_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_MODEL_BY_ID;
import static by.util.TextLabels.UPDATE_CAR_AD_POWER_BY_ID;

public class CarAdRepositoryImpl implements CarAdRepository {

    private final Connection connection = ConnectionFactory.getConnection();

    @Override
    public CarAdDAOResponse add(CarAdDAORequest carAdDAORequest) {
        try {
            connection.setAutoCommit(false);
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
            e.printStackTrace();
        }
        return get(carAdDAORequest);
    }

    @Override
    public CarAdDAOResponse delete(CarAdDAORequest carAdDAORequest) {
        CarAdDAOResponse carAdDAOResponse = get(carAdDAORequest);
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_AD);
            statement.setInt(1, carAdDAOResponse.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carAdDAOResponse;
    }

    @Override
    public List<CarAdDAOResponse> deleteAll() {
        List<CarAdDAOResponse> carAdDAOResponses = getAll();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CAR_ADS);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carAdDAOResponses;
    }

    @Override
    public CarAdDAOResponse get(CarAdDAORequest carAdDAORequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD);
            statement.setInt(1, carAdDAORequest.getOwnerId());
            statement.setTimestamp(2, carAdDAORequest.getCreationDate());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getCarAdDAOResponse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CarAdDAOResponse get(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CAR_AD_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getCarAdDAOResponse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CarAdDAOResponse> getAll() {
        List<CarAdDAOResponse> carAdDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CAR_ADS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarAdDAOResponse carAdDAOResponse = getCarAdDAOResponse(resultSet);
                carAdDAOResponses.add(carAdDAOResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carAdDAOResponses;
    }

    @Override
    public CarAdDAOResponse set(CarAdDAORequest carAdDAORequest) {
        CarAdDAOResponse carAdDAOResponse = get(carAdDAORequest);
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_AD_AGE_BY_ID);
            statement.setInt(1, carAdDAORequest.getAge());
            statement.setInt(2, carAdDAOResponse.getId());
            statement.execute();
            PreparedStatement statement2 = connection.prepareStatement(UPDATE_CAR_AD_BRAND_BY_ID);
            statement2.setString(1, carAdDAORequest.getBrand());
            statement2.setInt(2, carAdDAOResponse.getId());
            statement2.execute();
            PreparedStatement statement3 = connection.prepareStatement(UPDATE_CAR_AD_MODEL_BY_ID);
            statement3.setString(1, carAdDAORequest.getModel());
            statement3.setInt(2, carAdDAOResponse.getId());
            statement3.execute();
            PreparedStatement statement4 = connection.prepareStatement(UPDATE_CAR_AD_ENGINE_BY_ID);
            statement4.setInt(1, carAdDAORequest.getEngineSize());
            statement4.setInt(2, carAdDAOResponse.getId());
            statement4.execute();
            PreparedStatement statement5 = connection.prepareStatement(UPDATE_CAR_AD_POWER_BY_ID);
            statement5.setInt(1, carAdDAORequest.getEnginePower());
            statement5.setInt(2, carAdDAOResponse.getId());
            statement5.execute();
            PreparedStatement statement6 = connection.prepareStatement(UPDATE_CAR_AD_MILEAGE_BY_ID);
            statement6.setInt(1, carAdDAORequest.getMileage());
            statement6.setInt(2, carAdDAOResponse.getId());
            statement6.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(carAdDAORequest);
    }

    @Override
    public List<CarAdDAOResponse> getWithPagination(Integer size, Integer page) {
        List<CarAdDAOResponse> carAdDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_WITH_PAGINATION);
            int offset = (page - 1) * size;
            statement.setInt(1, offset);
            statement.setInt(2, size);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarAdDAOResponse carAdDAOResponse = getCarAdDAOResponse(resultSet);
                carAdDAOResponses.add(carAdDAOResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carAdDAOResponses;
    }

    private CarAdDAOResponse getCarAdDAOResponse(ResultSet resultSet) throws SQLException {
        CarAdDAOResponse carAdDAOResponse = new CarAdDAOResponse();
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

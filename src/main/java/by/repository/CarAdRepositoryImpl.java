package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarAdRepositoryImpl implements CarAdRepository {

    private static final String ADD_TO_CAR_AD = "INSERT INTO simple.public.car_ads VALUES (DEFAULT, ?, DEFAULT) ";
    private static final String DELETE_FROM_CAR_AD = "DELETE FROM simple.public.car_ads WHERE (chatid=? AND userid=?)";
    private static final String SELECT_ALL_CAR_AD = "SELECT * FROM simple.public.car_ads";
    private static final String SELECT_CAR_AD_BY_USER_ID_AND_CHAT_ID = "SELECT * FROM simple.public.car_ads WHERE (chatid=? AND userid=?)";
    private static final String SELECT_CAR_AD_BY_CHAT_ID = "SELECT * FROM simple.public.car_ads WHERE chatid=?";
    private static final String SELECT_CAR_AD_BY_USER_ID = "SELECT * FROM simple.public.car_ads WHERE userid=?";
    private static final String DELETE_ALL_CAR_AD = "DELETE FROM simple.public.car_ads";

    private static final String CHAT_ID = "chatid";
    private static final String CHAT_PART_ID = "chatpartid";
    private static final String USER_ID = "userid";

    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public CarAdDAOResponse add(CarAdDAORequest image) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_CAR_AD);
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
    public CarAdDAOResponse delete(CarAdDAORequest image) {
        return null;
    }

    @Override
    public List<CarAdDAOResponse> deleteAll() {
        return null;
    }

    @Override
    public CarAdDAOResponse get(CarAdDAORequest image) {
        return null;
    }

    @Override
    public List<CarAdDAOResponse> getAll() {
        return null;
    }

    @Override
    public List<CarAdDAOResponse> set(CarAdDAORequest image) {
        return null;
    }
}

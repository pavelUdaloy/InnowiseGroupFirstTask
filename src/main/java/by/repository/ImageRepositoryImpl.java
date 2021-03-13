package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageRepositoryImpl implements ImageRepository {
    private static final String ADD_TO_IMAGES = "INSERT INTO simple.public.images VALUES (DEFAULT, ?, ?, ?) ";
    private static final String DELETE_FROM_IMAGES = "DELETE FROM simple.public.images WHERE (chatid=? AND userid=?)";
    private static final String SELECT_ALL_IMAGES = "SELECT * FROM simple.public.images";
    private static final String SELECT_IMAGE_BY_IMAGES_ID = "SELECT * FROM simple.public.images WHERE chatid=?";
    private static final String SELECT_IMAGES_BY_CAR_AD_ID = "SELECT * FROM simple.public.images WHERE userid=?";
    private static final String DELETE_ALL_IMAGES = "DELETE FROM simple.public.images";

    private static final String OWNER_ID = "owner_id";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String FILE_FORMAT = "file_format";

    private Connection connection = ConnectionFactory.getConnection();

    public ImageRepositoryImpl() {
    }

    public static void main(String[] args) {
//        ImageDAORequest image = new ImageDAORequest();
//        image.setOwnerId(1);
//        image.setName("2010-120-120-10-101-10");
//        image.setFileFormat("png");
        System.out.println(new ImageRepositoryImpl().getAll());
    }

    @Override
    public ImageDAOResponse add(ImageDAORequest image) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_IMAGES);
            preparedStatement.setInt(1, image.getOwnerId());
            preparedStatement.setString(2, image.getName());
            preparedStatement.setString(3, image.getFileFormat());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get(image);
    }

    @Override
    public ImageDAOResponse delete(ImageDAORequest image) {
        return null;
    }

    @Override
    public List<ImageDAOResponse> deleteAll() {
        List<ImageDAOResponse> allImages = getAll();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_IMAGES);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allImages;
    }

    @Override
    public ImageDAOResponse get(ImageDAORequest image) {
        return null;
    }

    @Override
    public List<ImageDAOResponse> getAll() {
        List<ImageDAOResponse> chatParticipationList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_IMAGES);
            get(chatParticipationList, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatParticipationList;
    }

    @Override
    public List<ImageDAOResponse> set(ImageDAORequest image) {
        return null;
    }

    private void get(List<ImageDAOResponse> list, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ImageDAOResponse image = new ImageDAOResponse();
            image.setId(resultSet.getInt(ID));
            image.setName(resultSet.getString(NAME));
            image.setFileFormat(resultSet.getString(FILE_FORMAT));
            image.setOwnerId(resultSet.getInt(OWNER_ID));
            list.add(image);
        }
    }

}

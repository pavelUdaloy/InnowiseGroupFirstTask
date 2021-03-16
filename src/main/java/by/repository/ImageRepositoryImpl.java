package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.util.TextLabels.ADD_TO_IMAGES;
import static by.util.TextLabels.DELETE_ALL_IMAGES;
import static by.util.TextLabels.DELETE_IMAGE;
import static by.util.TextLabels.FILE_FORMAT;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.NAME;
import static by.util.TextLabels.OWNER_ID;
import static by.util.TextLabels.SELECT_ALL_IMAGES;
import static by.util.TextLabels.SELECT_IMAGE;
import static by.util.TextLabels.SELECT_IMAGE_BY_OWNER_ID;

public class ImageRepositoryImpl implements ImageRepository {

    private Connection connection;

    @Override
    public List<ImageDaoResponse> addAll(List<ImageDaoRequest> images) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponses = new ArrayList<>();
        for (ImageDaoRequest image : images) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_IMAGES)) {
                preparedStatement.setInt(1, image.getOwnerId());
                preparedStatement.setString(2, image.getName());
                preparedStatement.setString(3, image.getFileFormat());
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                throw new IncorrectSQLParametersException(e);
            }
            imageDaoResponses.add(get(image));
        }
        return imageDaoResponses;
    }

    @Override
    public ImageDaoResponse delete(ImageDaoRequest image) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        ImageDaoResponse imageDAOResponse = get(image);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_IMAGE)) {
            statement.setInt(1, image.getOwnerId());
            statement.setString(2, image.getName());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return imageDAOResponse;
    }

    @Override
    public List<ImageDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponses = getAll();
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ALL_IMAGES);
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return imageDaoResponses;
    }

    @Override
    public ImageDaoResponse get(ImageDaoRequest imageDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE)) {
            statement.setInt(1, imageDAORequest.getOwnerId());
            statement.setString(2, imageDAORequest.getName());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                ImageDaoResponse image = new ImageDaoResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                return image;
            } else return null;
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<ImageDaoResponse> getByOwnerId(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponse = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE_BY_OWNER_ID)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                ImageDaoResponse image = new ImageDaoResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDaoResponse.add(image);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return imageDaoResponse;
    }

    @Override
    public List<ImageDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponse = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_IMAGES);
            connection.commit();
            while (resultSet.next()) {
                ImageDaoResponse image = new ImageDaoResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDaoResponse.add(image);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return imageDaoResponse;
    }
}

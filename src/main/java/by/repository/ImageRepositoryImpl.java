package by.repository;

import by.db.ConnectionFactory;
import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
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

import static by.util.TextLabels.ADD_TO_IMAGES;
import static by.util.TextLabels.DELETE_ALL_IMAGES;
import static by.util.TextLabels.DELETE_IMAGE;
import static by.util.TextLabels.FILE_FORMAT;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.NAME;
import static by.util.TextLabels.OWNER_ID;
import static by.util.TextLabels.SELECT_ALL_IMAGES;
import static by.util.TextLabels.SELECT_IMAGE;
import static by.util.TextLabels.SELECT_IMAGE_BY_ID;
import static by.util.TextLabels.SELECT_IMAGE_BY_OWNER_ID;

public class ImageRepositoryImpl implements ImageRepository {

    private Connection connection;

    @Override
    public List<ImageDaoResponse> addAll(List<ImageDaoRequest> imageDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponses = new ArrayList<>();
        for (ImageDaoRequest image : imageDaoRequests) {
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
        if (imageDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return imageDaoResponses;
    }

    @Override
    public ImageDaoResponse delete(ImageDaoRequest imageDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        ImageDaoResponse imageDaoResponse = get(imageDaoRequest);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_IMAGE)) {
            statement.setInt(1, imageDaoRequest.getOwnerId());
            statement.setString(2, imageDaoRequest.getName());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        return imageDaoResponse;
    }

    @Override
    public List<ImageDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
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
    public ImageDaoResponse get(ImageDaoRequest imageDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE)) {
            statement.setInt(1, imageDaoRequest.getOwnerId());
            statement.setString(2, imageDaoRequest.getName());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                ImageDaoResponse imageDaoResponse = new ImageDaoResponse();
                imageDaoResponse.setId(resultSet.getInt(ID));
                imageDaoResponse.setName(resultSet.getString(NAME));
                imageDaoResponse.setFileFormat(resultSet.getString(FILE_FORMAT));
                imageDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                return imageDaoResponse;
            } else {
                throw new NullQueryException();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<ImageDaoResponse> getByOwnerId(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE_BY_OWNER_ID)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                ImageDaoResponse imageDaoResponse = new ImageDaoResponse();
                imageDaoResponse.setId(resultSet.getInt(ID));
                imageDaoResponse.setName(resultSet.getString(NAME));
                imageDaoResponse.setFileFormat(resultSet.getString(FILE_FORMAT));
                imageDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDaoResponses.add(imageDaoResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (imageDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return imageDaoResponses;
    }

    @Override
    public ImageDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                ImageDaoResponse imageDaoResponse = new ImageDaoResponse();
                imageDaoResponse.setId(id);
                imageDaoResponse.setName(resultSet.getString(NAME));
                imageDaoResponse.setFileFormat(resultSet.getString(FILE_FORMAT));
                imageDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                return imageDaoResponse;
            } else {
                throw new NullQueryException();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
    }

    @Override
    public List<ImageDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        connection = ConnectionFactory.getConnection();
        List<ImageDaoResponse> imageDaoResponses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_IMAGES);
            connection.commit();
            while (resultSet.next()) {
                ImageDaoResponse imageDaoResponse = new ImageDaoResponse();
                imageDaoResponse.setId(resultSet.getInt(ID));
                imageDaoResponse.setName(resultSet.getString(NAME));
                imageDaoResponse.setFileFormat(resultSet.getString(FILE_FORMAT));
                imageDaoResponse.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDaoResponses.add(imageDaoResponse);
            }
        } catch (SQLException e) {
            throw new IncorrectSQLParametersException(e);
        }
        if (imageDaoResponses.size() == 0) {
            throw new NullQueryException();
        }
        return imageDaoResponses;
    }
}

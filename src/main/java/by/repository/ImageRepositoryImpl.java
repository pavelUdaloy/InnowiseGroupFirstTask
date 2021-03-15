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

    private final Connection connection = ConnectionFactory.getConnection();

    @Override
    public List<ImageDAOResponse> addAll(List<ImageDAORequest> images) {
        List<ImageDAOResponse> imageDAOResponses = new ArrayList<>();
        for (ImageDAORequest image : images) {
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
            imageDAOResponses.add(get(image));
        }
        return imageDAOResponses;
    }

    @Override
    public ImageDAOResponse delete(ImageDAORequest image) {
        ImageDAOResponse imageDAOResponse = get(image);
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_IMAGE);
            statement.setInt(1, image.getOwnerId());
            statement.setString(2, image.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageDAOResponse;
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
    public ImageDAOResponse get(ImageDAORequest imageDAORequest) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE);
            statement.setInt(1, imageDAORequest.getOwnerId());
            statement.setString(2, imageDAORequest.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ImageDAOResponse image = new ImageDAOResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ImageDAOResponse> getByOwnerId(Integer ownerId) {
        List<ImageDAOResponse> imageDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_IMAGE_BY_OWNER_ID);
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ImageDAOResponse image = new ImageDAOResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDAOResponses.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageDAOResponses;
    }

    @Override
    public List<ImageDAOResponse> getAll() {
        List<ImageDAOResponse> imageDAOResponses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_IMAGES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ImageDAOResponse image = new ImageDAOResponse();
                image.setId(resultSet.getInt(ID));
                image.setName(resultSet.getString(NAME));
                image.setFileFormat(resultSet.getString(FILE_FORMAT));
                image.setOwnerId(resultSet.getInt(OWNER_ID));
                imageDAOResponses.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageDAOResponses;
    }
}

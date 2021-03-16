package by.repository;

import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface ImageRepository {
    List<ImageDaoResponse> addAll(List<ImageDaoRequest> images) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    ImageDaoResponse delete(ImageDaoRequest image) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    ImageDaoResponse get(ImageDaoRequest image) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

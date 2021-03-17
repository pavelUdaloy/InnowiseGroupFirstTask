package by.repository;

import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface ImageRepository {
    List<ImageDaoResponse> addAll(List<ImageDaoRequest> imageDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    ImageDaoResponse delete(ImageDaoRequest imageDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    ImageDaoResponse get(ImageDaoRequest imageDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

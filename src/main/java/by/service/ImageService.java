package by.service;

import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface ImageService {
    List<ImageDtoResponse> addAll(List<ImageDtoRequest> imageDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    ImageDtoResponse delete(ImageDtoRequest imageDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    ImageDtoResponse get(ImageDtoRequest imageDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDtoResponse> getByCarAdId(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    ImageDtoResponse get(Integer imageId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<ImageDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

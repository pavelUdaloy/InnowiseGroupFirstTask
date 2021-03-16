package by.service;

import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface ImageService {
    List<ImageDtoResponse> addAll(List<ImageDtoRequest> images) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    ImageDtoResponse delete(ImageDtoRequest image) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    ImageDtoResponse get(ImageDtoRequest image) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<ImageDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

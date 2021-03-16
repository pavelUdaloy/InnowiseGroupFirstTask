package by.service;

import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface CarAdService {
    CarAdDtoResponse add(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDtoResponse delete(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDtoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDtoResponse get(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDtoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDtoResponse update(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

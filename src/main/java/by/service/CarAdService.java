package by.service;

import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.servlet.CarAdServlet;

import java.util.List;

public interface CarAdService {
    CarAdDtoResponse add(CarAdDtoRequest carAdDtoRequest, List<ImageDtoRequest> imageDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDtoResponse delete(CarAdDtoRequest carAdDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdServlet.ResponseBody delete(Integer carAdId, Integer userId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDtoResponse get(CarAdDtoRequest carAdDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdServlet.ResponseBody get(Integer carAdId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDtoResponse update(Integer id, Integer age, String brand, String model, Integer engineSize, Integer enginePower, Integer mileage) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

package by.repository;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface CarAdRepository {
    CarAdDaoResponse add(CarAdDaoRequest carAdDaoRequest) throws ConnectionWithDBLostException, IncorrectSQLParametersException, NullQueryException;

    CarAdDaoResponse delete(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDaoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDaoResponse get(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    CarAdDaoResponse update(CarAdDaoRequest carAdDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<CarAdDaoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

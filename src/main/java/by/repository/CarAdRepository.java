package by.repository;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface CarAdRepository {
    CarAdDaoResponse add(CarAdDaoRequest carAdDAORequest) throws ConnectionWithDBLostException, IncorrectSQLParametersException;

    CarAdDaoResponse delete(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDaoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDaoResponse get(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    CarAdDaoResponse update(CarAdDaoRequest carAdDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<CarAdDaoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

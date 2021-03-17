package by.repository;

import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface UserRepository {
    UserDaoResponse add(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    UserDaoResponse delete(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    UserDaoResponse get(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

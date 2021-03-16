package by.repository;

import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface UserRepository {
    UserDaoResponse add(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    UserDaoResponse delete(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    UserDaoResponse get(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

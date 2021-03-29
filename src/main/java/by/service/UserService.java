package by.service;

import by.entity.base.User;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface UserService {
    User add(User user) throws IncorrectSQLParametersException, NullQueryException, ConnectionWithDBLostException;

    User delete(Integer id) throws IncorrectSQLParametersException, NullQueryException, ConnectionWithDBLostException;

    User get(String email) throws NullQueryException, ConnectionWithDBLostException;

    User get(Integer id) throws NullQueryException, ConnectionWithDBLostException;

    List<User> getWithPagination(Integer size, Integer page) throws NullQueryException, ConnectionWithDBLostException;
}

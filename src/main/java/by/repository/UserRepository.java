package by.repository;

import by.entity.base.User;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface UserRepository {
    User add(User user) throws IncorrectSQLParametersException, NullQueryException;

    User delete(Integer id) throws IncorrectSQLParametersException, NullQueryException;

    User get(String email) throws NullQueryException;

    User get(Integer id) throws NullQueryException;

    List<User> getWithPagination(Integer size, Integer page) throws NullQueryException;
}

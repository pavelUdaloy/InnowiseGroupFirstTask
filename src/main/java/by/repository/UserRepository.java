package by.repository;

import by.entity.base.User;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;

import java.util.List;

public interface UserRepository {
    User add(User user) throws NullQueryException;

    void delete(Integer id) throws NullQueryException;

    User get(String email) throws NullQueryException, ConnectionWithDBLostException;

    User get(Integer id) throws NullQueryException;

    List<User> getWithPagination(Integer size, Integer page) throws NullQueryException;

    void updateFirstAndLastName(User user) throws NullQueryException;
}

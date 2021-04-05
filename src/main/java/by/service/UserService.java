package by.service;

import by.entity.base.User;
import by.entity.dto.UserDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.servlet.response.user.AuthResponse;

import java.util.List;

public interface UserService {
    UserDto add(UserDto userDto) throws NullQueryException, ConnectionWithDBLostException;

    AuthResponse auth(UserDto userDto) throws ConnectionWithDBLostException, NullQueryException;

    void delete(Integer id) throws NullQueryException, ConnectionWithDBLostException;

    UserDto get(String email) throws NullQueryException, ConnectionWithDBLostException;

    UserDto get(Integer id) throws NullQueryException, ConnectionWithDBLostException;

    List<User> getWithPagination(Integer size, Integer page) throws NullQueryException, ConnectionWithDBLostException;

    UserDto update(User user) throws NullQueryException, ConnectionWithDBLostException;
}

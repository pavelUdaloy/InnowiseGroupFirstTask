package by.service;

import by.controller.response.user.AuthResponse;
import by.entity.base.User;
import by.entity.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto add(UserDto userDto);

    AuthResponse auth(UserDto userDto);

    void delete(Integer id);

    UserDto get(String email);

    UserDto get(Integer id);

    List<User> getWithPagination(Integer size, Integer page);

    UserDto update(User user);
}

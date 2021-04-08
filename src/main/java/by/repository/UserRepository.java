package by.repository;

import by.entity.base.User;

import java.util.List;

public interface UserRepository {
    Integer add(User user);

    Boolean auth(String firstName, String lastName, String email);

    void delete(Integer id);

    User get(String email);

    User get(Integer id);

    List<User> getWithPagination(Integer size, Integer page);

    void updateFirstAndLastName(User user);
}

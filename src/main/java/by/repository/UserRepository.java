package by.repository;

import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;

import java.util.List;

public interface UserRepository {
    UserDAOResponse add(UserDAORequest image);

    UserDAOResponse delete(UserDAORequest image);

    List<UserDAOResponse> deleteAll();

    UserDAOResponse get(UserDAORequest image);

    List<UserDAOResponse> getAll();

    List<UserDAOResponse> set(UserDAORequest image);
}

package by.repository;

import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;

import java.util.List;

public interface UserRepository {
    UserDAOResponse add(UserDAORequest userDAORequest);

    UserDAOResponse delete(UserDAORequest userDAORequest);

    List<UserDAOResponse> deleteAll();

    UserDAOResponse get(UserDAORequest userDAORequest);

    List<UserDAOResponse> getAll();
}

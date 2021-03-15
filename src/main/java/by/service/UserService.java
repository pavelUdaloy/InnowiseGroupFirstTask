package by.service;

import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;

import java.util.List;

public interface UserService {
    UserDAOResponse add(UserDAORequest userDAORequest);

    UserDAOResponse delete(UserDAORequest userDAORequest);

    List<UserDAOResponse> deleteAll();

    UserDAOResponse get(UserDAORequest userDAORequest);

    UserDAOResponse get(Integer id);

    List<UserDAOResponse> getAll();
}

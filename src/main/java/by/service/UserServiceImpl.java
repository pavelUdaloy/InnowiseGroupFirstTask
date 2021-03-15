package by.service;

import by.entity.dao.request.UserDAORequest;
import by.entity.dao.response.UserDAOResponse;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDAOResponse add(UserDAORequest userDAORequest) {
        return userRepository.add(userDAORequest);
    }

    @Override
    public UserDAOResponse delete(UserDAORequest userDAORequest) {
        return userRepository.delete(userDAORequest);
    }

    @Override
    public List<UserDAOResponse> deleteAll() {
        return userRepository.deleteAll();
    }

    @Override
    public UserDAOResponse get(UserDAORequest userDAORequest) {
        return userRepository.get(userDAORequest);
    }

    @Override
    public UserDAOResponse get(Integer id) {
        return userRepository.get(id);
    }

    @Override
    public List<UserDAOResponse> getAll() {
        return userRepository.getAll();
    }
}

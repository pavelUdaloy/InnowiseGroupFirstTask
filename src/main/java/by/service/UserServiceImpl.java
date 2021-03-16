package by.service;

import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDaoResponse add(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.add(userDAORequest);
    }

    @Override
    public UserDaoResponse delete(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.delete(userDAORequest);
    }

    @Override
    public List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.deleteAll();
    }

    @Override
    public UserDaoResponse get(UserDaoRequest userDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.get(userDAORequest);
    }

    @Override
    public UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.get(id);
    }

    @Override
    public List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        return userRepository.getAll();
    }
}

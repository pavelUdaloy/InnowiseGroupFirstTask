package by.service;

import by.entity.dao.request.UserDaoRequest;
import by.entity.dao.response.UserDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public UserDaoResponse add(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.add(userDaoRequest);
    }

    @Override
    public UserDaoResponse delete(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.delete(userDaoRequest);
    }

    @Override
    public List<UserDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.deleteAll();
    }

    @Override
    public UserDaoResponse get(UserDaoRequest userDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.get(userDaoRequest);
    }

    @Override
    public UserDaoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.get(id);
    }

    @Override
    public List<UserDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        return userRepository.getAll();
    }
}

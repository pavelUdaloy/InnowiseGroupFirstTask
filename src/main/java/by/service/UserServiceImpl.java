package by.service;

import by.controller.response.user.AuthResponse;
import by.controller.response.user.LogoutResponse;
import by.dao.EntityManagerInstance;
import by.entity.base.User;
import by.entity.dto.UserDto;
import by.exception.DaoOperationException;
import by.mapper.UserMapper;
import by.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManagerInstance entityManagerInstance;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, EntityManagerInstance entityManagerInstance) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.entityManagerInstance = entityManagerInstance;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = userMapper.convertDtoToUser(userDto);
        entityManagerInstance.getEntityManager().getTransaction().begin();
        Integer id;
        try {
            id = userRepository.add(user);
            entityManagerInstance.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            entityManagerInstance.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        }
        return get(id);
    }

    @Override
    public AuthResponse auth(UserDto userDto) {
        Boolean result = userRepository.auth(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        UserDto resultUserDto = get(userDto.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserDto(resultUserDto);
        authResponse.setAuth(result);
        return authResponse;
    }

    @Override
    public LogoutResponse logout() {
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setLogout(true);
        return logoutResponse;
    }

    @Override
    public void delete(Integer id) {
        entityManagerInstance.getEntityManager().getTransaction().begin();
        try {
            userRepository.delete(id);
            entityManagerInstance.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            entityManagerInstance.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        }
    }

    @Override
    public UserDto get(String email) {
        User result = userRepository.get(email);
        return userMapper.convertUserToDto(result);
    }

    @Override
    public UserDto get(Integer id) {
        User result = userRepository.get(id);
        return userMapper.convertUserToDto(result);
    }


    @Override
    public List<User> getWithPagination(Integer size, Integer page) {
        List<User> results = userRepository.getWithPagination(size, page);
        return results;
    }

    @Override
    public UserDto update(User user) {
        entityManagerInstance.getEntityManager().getTransaction().begin();
        try {
            userRepository.updateFirstAndLastName(user);
            entityManagerInstance.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            entityManagerInstance.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        }
        return get(user.getId());
    }
}

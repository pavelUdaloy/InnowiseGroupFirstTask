package by.service;

import by.controller.response.user.AuthResponse;
import by.controller.response.user.LogoutResponse;
import by.dao.EntityManagerProvider;
import by.entity.base.User;
import by.entity.dto.UserDto;
import by.exception.DaoOperationException;
import by.mapper.UserMapper;
import by.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = userMapper.convertDtoToUser(userDto);
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Integer id;
        try {
            id = userRepository.add(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return get(id);
    }

    @Override
    public AuthResponse auth(UserDto userDto) {
        Boolean result;
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            result = userRepository.auth(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
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
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.delete(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
    }

    @Override
    public UserDto get(String email) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        User result;
        try {
            result = userRepository.get(email);
        } catch (RuntimeException e) {
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return userMapper.convertUserToDto(result);
    }

    @Override
    public UserDto get(Integer id) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        User result;
        try {
            result = userRepository.get(id);
        } catch (RuntimeException e) {
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return userMapper.convertUserToDto(result);
    }


    @Override
    public List<User> getWithPagination(Integer size, Integer page) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        List<User> results = null;
        try {
            results = userRepository.getWithPagination(size, page);
        } catch (RuntimeException e) {
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return results;
    }

    @Override
    public UserDto update(User user) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.updateFirstAndLastName(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return get(user.getId());
    }
}

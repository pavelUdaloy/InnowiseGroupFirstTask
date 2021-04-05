package by.service;

import by.entity.base.User;
import by.entity.dto.UserDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.mapper.UserMapper;
import by.provider.EntityManagerProvider;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;
import by.servlet.response.user.AuthResponse;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserDto add(UserDto userDto) throws ConnectionWithDBLostException, NullQueryException {
        User user = userMapper.convertDtoToUser(userDto);
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Integer id = null;
        try {
            id = userRepository.add(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        if (id == null) {
            throw new NullQueryException();
        } else {
            return get(id);
        }
    }

    @Override
    public AuthResponse auth(UserDto userDto) throws ConnectionWithDBLostException, NullQueryException {
        Boolean result = false;
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            result = userRepository.auth(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
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
    public void delete(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.delete(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException | RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
        } finally {
            EntityManagerProvider.clear();
        }
    }

    @Override
    public UserDto get(String email) throws ConnectionWithDBLostException, NullQueryException {
        User result = null;
        try {
            result = userRepository.get(email);
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        return userMapper.convertUserToDto(result);
    }

    @Override
    public UserDto get(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        User result = null;
        try {
            result = userRepository.get(id);
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        return userMapper.convertUserToDto(result);
    }


    @Override
    public List<User> getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException {
        List<User> results = null;
        try {
            results = userRepository.getWithPagination(size, page);
        } catch (NullQueryException e) {
            System.out.println(e);
        } finally {
            EntityManagerProvider.clear();
        }
        return results;
    }

    @Override
    public UserDto update(User user) throws NullQueryException, ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.updateFirstAndLastName(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        return get(user.getId());
    }
}

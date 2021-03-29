package by.service;

import by.db.EntityManagerProvider;
import by.entity.base.User;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User add(User user) throws ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        User result = null;
        try {
            result = userRepository.add(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (IncorrectSQLParametersException | NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
        } finally {
            EntityManagerProvider.clear();
        }
        return result;
    }

    @Override
    public User delete(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        User result = null;
        try {
            result = userRepository.delete(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (IncorrectSQLParametersException | NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
        } finally {
            EntityManagerProvider.clear();
        }
        return result;
    }

    @Override
    public User get(String email) throws ConnectionWithDBLostException {
        User result = null;
        try {
            result = userRepository.get(email);
        } catch (NullQueryException e) {
            System.out.println(e);
        } finally {
            EntityManagerProvider.clear();
        }
        return result;
    }

    @Override
    public User get(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        User result = null;
        try {
            result = userRepository.get(id);
        } catch (NullQueryException e) {
            System.out.println(e);
        } finally {
            EntityManagerProvider.clear();
        }
        return result;
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
}

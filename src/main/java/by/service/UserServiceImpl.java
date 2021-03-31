package by.service;

import by.db.EntityManagerProvider;
import by.entity.base.User;
import by.exception.ConnectionWithDBLostException;
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
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
        } finally {
            EntityManagerProvider.clear();
        }
        return result;
    }

    @Override
    public void delete(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.delete(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
        } finally {
            EntityManagerProvider.clear();
        }
    }

    @Override
    public User get(String email) throws ConnectionWithDBLostException, NullQueryException {
        User result = null;
        try {
            result = userRepository.get(email);
        } catch (NullQueryException e) {
            throw new NullQueryException();
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
            throw new NullQueryException();
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

    @Override
    public User update(User user) throws NullQueryException, ConnectionWithDBLostException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            userRepository.updateFirstAndLastName(user);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } finally {
            EntityManagerProvider.clear();
        }
        return get(user.getId());
    }
}

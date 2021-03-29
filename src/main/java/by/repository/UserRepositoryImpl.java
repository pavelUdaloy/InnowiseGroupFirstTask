package by.repository;

import by.db.EntityManagerProvider;
import by.entity.base.User;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import org.hibernate.Session;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User add(User user) throws IncorrectSQLParametersException, NullQueryException {
        try {
            EntityManagerProvider.getEntityManager().persist(user);//todo trnsctn open and close
        } catch (Exception e) {
            throw new IncorrectSQLParametersException(e);
        }
        return get(user.getEmail());
    }

    @Override
    public User delete(Integer id) throws IncorrectSQLParametersException, NullQueryException {
        User user = get(id);
        try {
            EntityManagerProvider.getEntityManager().remove(user);
        } catch (Exception e) {
            throw new IncorrectSQLParametersException(e);
        }
        return user;
    }

    @Override
    public User get(String email) throws NullQueryException {
        try {
            return EntityManagerProvider.getEntityManager().unwrap(Session.class)
                    .bySimpleNaturalId(User.class).load(email);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public User get(Integer id) throws NullQueryException {
        try {
            return EntityManagerProvider.getEntityManager().unwrap(Session.class)
                    .get(User.class, id);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public List<User> getWithPagination(Integer size, Integer page) throws NullQueryException {
        try {
            return EntityManagerProvider.getEntityManager()
                    .createQuery("from User u ORDER BY u.id desc", User.class)
                    .setFirstResult((page-1)*size).setMaxResults(size).getResultList();
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }
}

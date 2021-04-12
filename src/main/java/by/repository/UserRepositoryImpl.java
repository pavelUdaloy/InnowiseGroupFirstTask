package by.repository;

import by.dao.EntityManagerInstance;
import by.entity.base.User;
import by.exception.DaoOperationException;
import by.exception.EmptyDbAnswerException;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManagerInstance entityManagerInstance;

    public UserRepositoryImpl(EntityManagerInstance entityManagerInstance) {
        this.entityManagerInstance = entityManagerInstance;
    }

    @Override
    public Integer add(User user) {
        Integer id;
        try {
            id = (Integer) entityManagerInstance.getEntityManager().unwrap(Session.class).save(user);
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
        if (id == null) {
            throw new EmptyDbAnswerException();
        } else {
            return id;
        }
    }

    @Override
    public Boolean auth(String firstName, String lastName, String email) {
        try {
            User user = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u WHERE u.email = :email " +
                            "AND u.lastName = :lastName AND u.firstName = :firstName", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("email", email).setParameter("lastName", lastName)
                    .setParameter("firstName", firstName).getSingleResult();
            return user != null;
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        User user = new User();
        user.setId(id);
        try {
            entityManagerInstance.getEntityManager().remove(user);
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public User get(String email) {
        try {
            User user = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.telephones WHERE u.email = :email", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("email", email).getSingleResult();
            user = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :user", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("user", user).getSingleResult();
            if (user == null) {
                throw new EmptyDbAnswerException();
            } else {
                return user;
            }
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public User get(Integer id) {
        try {
            User user = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.telephones WHERE u.id = :id", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
            user = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :user", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("user", user).getSingleResult();
            if (user == null) {
                throw new EmptyDbAnswerException();
            } else {
                return user;
            }
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public List<User> getWithPagination(Integer size, Integer page) {
        try {
            List<User> users = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u LEFT JOIN FETCH u.telephones ORDER BY u.id desc", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
            users = entityManagerInstance.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :users", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("users", users).getResultList();
            if (users.size() == 0) {
                throw new EmptyDbAnswerException();
            } else {
                return users;
            }
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public void updateFirstAndLastName(User user) {
        try {
            entityManagerInstance.getEntityManager().unwrap(Session.class)
                    .createQuery("update User u set u.firstName = :newFirstName, u.lastName = :newLastName where u.id = :id")
                    .setParameter("newFirstName", user.getFirstName())
                    .setParameter("newLastName", user.getLastName())
                    .setParameter("id", user.getId()).executeUpdate();
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }
}

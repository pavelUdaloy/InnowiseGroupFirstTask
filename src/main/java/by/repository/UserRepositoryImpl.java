package by.repository;

import by.db.EntityManagerProvider;
import by.entity.base.CarAd;
import by.entity.base.User;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User add(User user) throws NullQueryException {
        Integer id;
        try {
            id = (Integer) EntityManagerProvider.getEntityManager().unwrap(Session.class).save(user);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
        return get(id);
    }

    @Override
    public void delete(Integer id) throws NullQueryException {
        User user = new User();
        user.setId(id);
        try {
            EntityManagerProvider.getEntityManager().remove(user);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public User get(String email) throws NullQueryException, ConnectionWithDBLostException {
        try {
            User user = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.telephones WHERE u.email = :email", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("email", email).getSingleResult();
            user = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :user", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("user", user).getSingleResult();
            if (user == null) {
                throw new NullQueryException();
            } else {
                List<CarAd> carAdsFinal = new ArrayList<>();
                for (CarAd carAd : user.getCarAds()) {
                    List<CarAd> carAds = EntityManagerProvider.getEntityManager()
                            .createQuery("select distinct c from CarAd c  LEFT JOIN FETCH c.images WHERE c.id = :id", CarAd.class)
                            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                            .setParameter("id", carAd.getId()).getResultList();
                    if (carAds.size() == 1) {
                        carAdsFinal.add(carAds.get(0));
                    }
                }
                user.setCarAds(carAdsFinal);
                return user;
            }
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public User get(Integer id) throws NullQueryException {
        try {
            User user = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.telephones WHERE u.id = :id", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
            user = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :user", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("user", user).getSingleResult();
            if (user == null) {
                throw new NullQueryException();
            } else {
                List<CarAd> carAdsFinal = new ArrayList<>();
                for (CarAd carAd : user.getCarAds()) {
                    List<CarAd> carAds = EntityManagerProvider.getEntityManager()
                            .createQuery("select distinct c from CarAd c  LEFT JOIN FETCH c.images WHERE c.id = :id", CarAd.class)
                            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                            .setParameter("id", carAd.getId()).getResultList();
                    if (carAds.size() == 1) {
                        carAdsFinal.add(carAds.get(0));
                    }
                }
                user.setCarAds(carAdsFinal);
                return user;
            }
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public List<User> getWithPagination(Integer size, Integer page) throws NullQueryException {
        try {
            List<User> users = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u LEFT JOIN FETCH u.telephones ORDER BY u.id desc", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
            users = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct u from User u  LEFT JOIN FETCH u.carAds WHERE u in :users", User.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("users", users).getResultList();
            if (users.size() == 0) {
                throw new NullQueryException();
            } else {
                for (User user : users) {
                    List<CarAd> carAdsFinal = new ArrayList<>();
                    for (CarAd carAd : user.getCarAds()) {
                        List<CarAd> carAds = EntityManagerProvider.getEntityManager()
                                .createQuery("select distinct c from CarAd c  LEFT JOIN FETCH c.images WHERE c.id = :id", CarAd.class)
                                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                                .setParameter("id", carAd.getId()).getResultList();
                        if (carAds.size() == 1) {
                            carAdsFinal.add(carAds.get(0));
                        }
                    }
                    user.setCarAds(carAdsFinal);
                }
                return users;
            }
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public void updateFirstAndLastName(User user) throws NullQueryException {
        try {
            EntityManagerProvider.getEntityManager().unwrap(Session.class)
                    .createQuery("update User u set u.firstName = :newFirstName, u.lastName = :newLastName where u.id = :id")
                    .setParameter("newFirstName", user.getFirstName())
                    .setParameter("newLastName", user.getLastName())
                    .setParameter("id", user.getId()).executeUpdate();
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }
}

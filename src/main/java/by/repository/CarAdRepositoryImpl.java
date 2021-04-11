package by.repository;

import by.dao.SessionFactory;
import by.entity.base.CarAd;
import by.exception.DaoOperationException;
import by.exception.EmptyDbAnswerException;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarAdRepositoryImpl implements CarAdRepository {

    private final SessionFactory sessionFactory;

    public CarAdRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer add(CarAd carAd) {
        Integer id;
        try {
            id = (Integer) sessionFactory.getEntityManager().unwrap(Session.class).save(carAd);
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
    public void delete(CarAd carAd) {
        try {
            sessionFactory.getEntityManager().remove(carAd);
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public CarAd get(Integer id) {
        CarAd carAd;
        try {
            carAd = sessionFactory.getEntityManager()
                    .createQuery("select distinct c from CarAd c  LEFT JOIN FETCH c.images WHERE c.id = :id", CarAd.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DaoOperationException();
        }
        if (carAd == null) {
            throw new EmptyDbAnswerException();
        } else {
            return carAd;
        }
    }

    @Override
    public void update(CarAd carAd) {
        try {
            sessionFactory.getEntityManager().unwrap(Session.class)
                    .createQuery("update CarAd c set c.age = :age," +
                            " c.brand = :brand, " +
                            " c.mileage = :mileage, " +
                            " c.model = :model, " +
                            " c.lastEditDate = :lastEditDate, " +
                            " c.enginePower = :enginePower, " +
                            " c.engineSize = :engineSize " +
                            "where c.id = :id")
                    .setParameter("age", carAd.getAge())
                    .setParameter("brand", carAd.getBrand())
                    .setParameter("mileage", carAd.getMileage())
                    .setParameter("model", carAd.getModel())
                    .setParameter("lastEditDate", carAd.getLastEditDate())
                    .setParameter("enginePower", carAd.getEnginePower())
                    .setParameter("engineSize", carAd.getEngineSize())
                    .setParameter("id", carAd.getId())
                    .executeUpdate();
        } catch (Exception e) {
            throw new DaoOperationException(e);
        }
    }

    @Override
    public List<CarAd> getWithPagination(Integer size, Integer page) {
        List<CarAd> carAds;
        try {
            carAds = sessionFactory.getEntityManager()
                    .createQuery("select distinct c from CarAd c LEFT JOIN FETCH c.images ORDER BY c.lastEditDate desc", CarAd.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
        } catch (Exception e) {
            throw new DaoOperationException();
        }
        if (carAds == null || carAds.size() == 0) {
            throw new EmptyDbAnswerException();
        } else {
            return carAds;
        }
    }
}

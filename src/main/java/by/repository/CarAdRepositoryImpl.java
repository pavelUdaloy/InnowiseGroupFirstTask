package by.repository;

import by.entity.base.CarAd;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.provider.EntityManagerProvider;
import by.servlet.request.UpdateAdRequest;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;

import java.util.List;

public class CarAdRepositoryImpl implements CarAdRepository {

    @Override
    public Integer add(CarAd carAd) throws ConnectionWithDBLostException, NullQueryException {
        Integer id;
        try {
            id = (Integer) EntityManagerProvider.getEntityManager().unwrap(Session.class).save(carAd);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
        return id;
    }

    @Override
    public void delete(Integer id) throws ConnectionWithDBLostException, NullQueryException {
        CarAd carAd = new CarAd();
        carAd.setId(id);
        try {
            EntityManagerProvider.getEntityManager().remove(carAd);
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public CarAd get(Integer id) throws NullQueryException, ConnectionWithDBLostException {
        CarAd carAd;
        try {
            carAd = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct c from CarAd c  LEFT JOIN FETCH c.images WHERE c.id = :id", CarAd.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new ConnectionWithDBLostException();
        }
        if (carAd == null) {
            throw new NullQueryException();
        } else {
            return carAd;
        }
    }

    @Override
    public void update(UpdateAdRequest updateAdRequest) throws ConnectionWithDBLostException, NullQueryException {
        try {
            EntityManagerProvider.getEntityManager().unwrap(Session.class)
                    .createQuery("update CarAd c set c.age = :age," +
                            " c.brand = :brand, " +
                            " c.mileage = :mileage, " +
                            " c.model = :model, " +
                            " c.lastEditDate = :lastEditDate, " +
                            " c.enginePower = :enginePower, " +
                            " c.engineSize = :engineSize " +
                            "where c.id = :id")
                    .setParameter("age", updateAdRequest.getAge())
                    .setParameter("brand", updateAdRequest.getBrand())
                    .setParameter("mileage", updateAdRequest.getMileage())
                    .setParameter("model", updateAdRequest.getModel())
                    .setParameter("lastEditDate", updateAdRequest.getLastEditDate())
                    .setParameter("enginePower", updateAdRequest.getEnginePower())
                    .setParameter("engineSize", updateAdRequest.getEngineSize())
                    .setParameter("id", updateAdRequest.getId())
                    .executeUpdate();
        } catch (Exception e) {
            throw new NullQueryException(e);
        }
    }

    @Override
    public List<CarAd> getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException, NullQueryException {
        List<CarAd> carAds;
        try {
            carAds = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct c from CarAd c LEFT JOIN FETCH c.images ORDER BY c.lastEditDate desc", CarAd.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
        } catch (Exception e) {
            throw new ConnectionWithDBLostException();
        }
        if (carAds == null) {
            throw new NullQueryException();
        } else {
            return carAds;
        }
    }
}

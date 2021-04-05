package by.repository;

import by.entity.base.Image;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.provider.EntityManagerProvider;
import org.hibernate.annotations.QueryHints;

public class ImageRepositoryImpl implements ImageRepository {
    @Override
    public Image get(Integer id) throws ConnectionWithDBLostException, NullQueryException {
        Image image;
        try {
            image = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct i from Image i WHERE i.id = :id", Image.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new ConnectionWithDBLostException();
        }
        if (image == null) {
            throw new NullQueryException();
        } else {
            return image;
        }
    }
}

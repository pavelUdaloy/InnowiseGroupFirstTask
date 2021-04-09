package by.repository;

import by.dao.EntityManagerProvider;
import by.entity.base.Image;
import by.exception.DaoOperationException;
import by.exception.EmptyDbAnswerException;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
    @Override
    public Image get(Integer id) {
        Image image;
        try {
            image = EntityManagerProvider.getEntityManager()
                    .createQuery("select distinct i from Image i WHERE i.id = :id", Image.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DaoOperationException();
        }
        if (image == null) {
            throw new EmptyDbAnswerException();
        } else {
            return image;
        }
    }
}

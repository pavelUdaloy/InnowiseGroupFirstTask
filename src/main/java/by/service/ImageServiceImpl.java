package by.service;

import by.entity.base.Image;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.mapper.ImageMapper;
import by.provider.EntityManagerProvider;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;
import by.servlet.response.ad.GetImageResponse;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();
    private final ImageMapper imageMapper = new ImageMapper();

    @Override
    public GetImageResponse get(Integer id) throws ConnectionWithDBLostException, NullQueryException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Image image;
        try {
            image = imageRepository.get(id);
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
        if (image == null) {
            throw new NullQueryException();
        }
        return imageMapper.convertImageToDtoResponse(image);
    }
}

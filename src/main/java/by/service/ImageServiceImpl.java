package by.service;

import by.controller.response.ad.GetImageResponse;
import by.dao.EntityManagerProvider;
import by.entity.base.Image;
import by.exception.DaoOperationException;
import by.exception.NullQueryException;
import by.mapper.ImageMapper;
import by.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public GetImageResponse get(Integer id) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Image image;
        try {
            image = imageRepository.get(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        if (image == null) {
            throw new NullQueryException();
        }
        return imageMapper.convertImageToDtoResponse(image);
    }
}

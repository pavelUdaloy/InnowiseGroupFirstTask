package by.service;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();

    @Override
    public ImageDAOResponse add(ImageDAORequest image) {
        return imageRepository.add(image);
    }

    @Override
    public ImageDAOResponse delete(ImageDAORequest image) {
        return imageRepository.delete(image);
    }

    @Override
    public List<ImageDAOResponse> deleteAll() {
        return imageRepository.deleteAll();
    }

    @Override
    public ImageDAOResponse get(ImageDAORequest image) {
        return imageRepository.get(image);
    }

    @Override
    public List<ImageDAOResponse> getAll() {
        return imageRepository.getAll();
    }
}

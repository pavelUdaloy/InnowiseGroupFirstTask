package by.service;

import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.ImageDTOResponse;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();

    @Override
    public List<ImageDTOResponse> addAll(List<ImageDTORequest> images) {
        List<ImageDTOResponse> imageDAOResponses = new ArrayList<>();
        for (ImageDTORequest image : images) {
            imageDAOResponses.add(imageRepository.add(image));
        }
        return imageDAOResponses;
    }

    @Override
    public ImageDTOResponse delete(ImageDTORequest image) {
        return imageRepository.delete(image);
    }

    @Override
    public List<ImageDTOResponse> deleteAll() {
        return imageRepository.deleteAll();
    }

    @Override
    public ImageDTOResponse get(ImageDTORequest image) {
        return imageRepository.get(image);
    }

    @Override
    public List<ImageDTOResponse> getAll() {
        return imageRepository.getAll();
    }
}

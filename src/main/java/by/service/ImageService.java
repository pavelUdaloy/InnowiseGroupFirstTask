package by.service;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;

import java.util.List;

public interface ImageService {
    ImageDAOResponse add(ImageDAORequest image);

    ImageDAOResponse delete(ImageDAORequest image);

    List<ImageDAOResponse> deleteAll();

    ImageDAOResponse get(ImageDAORequest image);

    List<ImageDAOResponse> getAll();
}

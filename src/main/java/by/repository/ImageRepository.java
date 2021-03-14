package by.repository;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;

import java.util.List;

public interface ImageRepository {
    ImageDAOResponse add(ImageDAORequest image);

    ImageDAOResponse delete(ImageDAORequest image);

    List<ImageDAOResponse> deleteAll();

    ImageDAOResponse get(ImageDAORequest image);

    List<ImageDAOResponse> getAll();
}

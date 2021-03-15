package by.repository;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;

import java.util.List;

public interface ImageRepository {
    List<ImageDAOResponse> addAll(List<ImageDAORequest> images);

    ImageDAOResponse delete(ImageDAORequest image);

    List<ImageDAOResponse> deleteAll();

    ImageDAOResponse get(ImageDAORequest image);

    List<ImageDAOResponse> getByOwnerId(Integer ownerId);

    List<ImageDAOResponse> getAll();
}

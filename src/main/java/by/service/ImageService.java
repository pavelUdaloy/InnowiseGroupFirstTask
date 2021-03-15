package by.service;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;
import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.ImageDTOResponse;

import java.util.List;

public interface ImageService {
    List<ImageDTOResponse> addAll(List<ImageDTORequest> images);

    ImageDTOResponse delete(ImageDTORequest image);

    List<ImageDTOResponse> deleteAll();

    ImageDTOResponse get(ImageDTORequest image);

    List<ImageDTOResponse> getAll();
}

package by.service;

import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.ImageDTOResponse;

import java.util.List;

public interface ImageService {
    List<ImageDTOResponse> addAll(List<ImageDTORequest> images);

    ImageDTOResponse delete(ImageDTORequest image);

    List<ImageDTOResponse> deleteAll();

    ImageDTOResponse get(ImageDTORequest image);

    List<ImageDTOResponse> getByOwnerId(Integer ownerId);

    List<ImageDTOResponse> getAll();
}

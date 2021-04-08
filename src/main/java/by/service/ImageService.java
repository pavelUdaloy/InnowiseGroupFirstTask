package by.service;

import by.controller.response.ad.GetImageResponse;

public interface ImageService {
    GetImageResponse get(Integer imageId);
}

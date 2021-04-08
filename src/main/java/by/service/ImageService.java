package by.service;

import by.controller.response.ad.GetImageResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;

public interface ImageService {
    GetImageResponse get(Integer imageId) throws ConnectionWithDBLostException, NullQueryException;
}

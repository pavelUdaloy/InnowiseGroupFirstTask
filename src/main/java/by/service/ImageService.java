package by.service;

import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.servlet.responseentity.GetImageResponse;

public interface ImageService {
    GetImageResponse get(Integer imageId) throws ConnectionWithDBLostException, NullQueryException;
}

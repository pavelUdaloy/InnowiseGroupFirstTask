package by.repository;

import by.entity.base.Image;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;

public interface ImageRepository {
    Image get(Integer imageId) throws ConnectionWithDBLostException, NullQueryException;
}

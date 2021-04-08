package by.repository;

import by.entity.base.Image;

public interface ImageRepository {
    Image get(Integer imageId);
}

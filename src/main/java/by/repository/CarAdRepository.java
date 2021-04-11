package by.repository;

import by.entity.base.CarAd;

import java.util.List;

public interface CarAdRepository {
    Integer add(CarAd carAd);

    void delete(CarAd carAd);

    CarAd get(Integer id);

    void update(CarAd carAd);

    List<CarAd> getWithPagination(Integer size, Integer page);
}

package by.repository;

import by.controller.request.ad.UpdateAdRequest;
import by.entity.base.CarAd;

import java.util.List;

public interface CarAdRepository {
    Integer add(CarAd carAd);

    void delete(Integer id);

    CarAd get(Integer id);

    void update(UpdateAdRequest updateAdRequest);

    List<CarAd> getWithPagination(Integer size, Integer page);
}

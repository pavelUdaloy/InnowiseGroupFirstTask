package by.repository;

import by.controller.request.ad.UpdateAdRequest;
import by.entity.base.CarAd;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;

import java.util.List;

public interface CarAdRepository {
    Integer add(CarAd carAd) throws ConnectionWithDBLostException, NullQueryException;

    void delete(Integer id) throws ConnectionWithDBLostException, NullQueryException;

    CarAd get(Integer id) throws NullQueryException, ConnectionWithDBLostException;

    void update(UpdateAdRequest updateAdRequest) throws ConnectionWithDBLostException, NullQueryException;

    List<CarAd> getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException, NullQueryException;
}

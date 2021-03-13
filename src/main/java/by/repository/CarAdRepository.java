package by.repository;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;

import java.util.List;

public interface CarAdRepository {
    CarAdDAOResponse add(CarAdDAORequest image);

    CarAdDAOResponse delete(CarAdDAORequest image);

    List<CarAdDAOResponse> deleteAll();

    CarAdDAOResponse get(CarAdDAORequest image);

    List<CarAdDAOResponse> getAll();

    List<CarAdDAOResponse> set(CarAdDAORequest image);
}

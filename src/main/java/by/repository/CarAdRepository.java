package by.repository;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;

import java.util.List;

public interface CarAdRepository {
    CarAdDAOResponse add(CarAdDAORequest carAdDAORequest);

    CarAdDAOResponse delete(CarAdDAORequest carAdDAORequest);

    List<CarAdDAOResponse> deleteAll();

    CarAdDAOResponse get(CarAdDAORequest carAdDAORequest);

    CarAdDAOResponse get(Integer id);

    List<CarAdDAOResponse> getAll();

    CarAdDAOResponse set(CarAdDAORequest carAdDAORequest);

    List<CarAdDAOResponse> getWithPagination(Integer size, Integer page);
}

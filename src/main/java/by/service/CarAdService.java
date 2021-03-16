package by.service;

import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.response.CarAdDTOResponse;

import java.util.List;

public interface CarAdService {
    CarAdDTOResponse add(CarAdDTORequest carAdDTORequest);

    CarAdDTOResponse delete(CarAdDTORequest carAdDTORequest);

    CarAdDTOResponse delete(Integer id);

    List<CarAdDTOResponse> deleteAll();

    CarAdDTOResponse get(CarAdDTORequest carAdDTORequest);

    CarAdDTOResponse get(Integer id);

    List<CarAdDTOResponse> getAll();

    CarAdDTOResponse set(CarAdDTORequest carAdDTORequest);

    List<CarAdDTOResponse> getWithPagination(Integer size, Integer page);
}

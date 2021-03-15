package by.service;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;
import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.response.CarAdDTOResponse;

import java.util.List;

public interface CarAdService {
    CarAdDTOResponse add(CarAdDTORequest carAdDTORequest);

    CarAdDTOResponse delete(CarAdDTORequest carAdDTORequest);

    List<CarAdDTOResponse> deleteAll();

    CarAdDTOResponse get(CarAdDTORequest carAdDTORequest);

    List<CarAdDTOResponse> getAll();

    CarAdDTOResponse set(CarAdDTORequest carAdDTORequest);
}

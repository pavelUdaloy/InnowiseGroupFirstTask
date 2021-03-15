package by.service;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;
import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.response.CarAdDTOResponse;
import by.mapper.CarAdMapper.DTODAOMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;

import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    @Override
    public CarAdDTOResponse add(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDAOResponse add = carAdRepository.add(carAdDAORequest);
        return null;
    }

    @Override
    public CarAdDTOResponse delete(CarAdDTORequest carAdDTORequest) {
        return carAdRepository.delete(carAdDTORequest);
    }

    @Override
    public List<CarAdDTOResponse> deleteAll() {
        return carAdRepository.deleteAll();
    }

    @Override
    public CarAdDTOResponse get(CarAdDTORequest carAdDTORequest) {
        return carAdRepository.get(carAdDTORequest);
    }

    @Override
    public List<CarAdDTOResponse> getAll() {
        return carAdRepository.getAll();
    }

    @Override
    public CarAdDTOResponse set(CarAdDTORequest carAdDTORequest) {
        return carAdRepository.set(carAdDTORequest);
    }
}

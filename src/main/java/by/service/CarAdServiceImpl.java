package by.service;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;

import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    @Override
    public CarAdDAOResponse add(CarAdDAORequest carAdDAORequest) {
        return carAdRepository.add(carAdDAORequest);
    }

    @Override
    public CarAdDAOResponse delete(CarAdDAORequest carAdDAORequest) {
        return carAdRepository.delete(carAdDAORequest);
    }

    @Override
    public List<CarAdDAOResponse> deleteAll() {
        return carAdRepository.deleteAll();
    }

    @Override
    public CarAdDAOResponse get(CarAdDAORequest carAdDAORequest) {
        return carAdRepository.get(carAdDAORequest);
    }

    @Override
    public List<CarAdDAOResponse> getAll() {
        return carAdRepository.getAll();
    }

    @Override
    public CarAdDAOResponse set(CarAdDAORequest carAdDAORequest) {
        return carAdRepository.set(carAdDAORequest);
    }
}

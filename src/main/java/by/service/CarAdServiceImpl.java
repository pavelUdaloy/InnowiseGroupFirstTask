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
        CarAdDAOResponse carAdDAOResponse = carAdRepository.add(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDTOResponse delete(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDAOResponse carAdDAOResponse = carAdRepository.delete(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDTOResponse> deleteAll() {
        List<CarAdDAOResponse> carAdDAOResponses = carAdRepository.deleteAll();
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAOResponses);
    }

    @Override
    public CarAdDTOResponse get(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDAOResponse carAdDAOResponse = carAdRepository.get(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDTOResponse get(Integer id) {
        CarAdDAOResponse carAdDAOResponse = carAdRepository.get(id);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDTOResponse> getAll() {
        List<CarAdDAOResponse> carAdDAOResponses = carAdRepository.getAll();
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAOResponses);
    }

    @Override
    public CarAdDTOResponse set(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDAOResponse carAdDAOResponse = carAdRepository.set(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDTOResponse> getWithPagination(Integer size, Integer page) {
        List<CarAdDAOResponse> carAdDAOResponses = carAdRepository.getWithPagination(size, page);
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAOResponses);
    }
}

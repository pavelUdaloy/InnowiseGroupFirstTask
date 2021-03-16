package by.service;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.CarAdMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;

import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    @Override
    public CarAdDtoResponse add(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = CarAdMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.add(carAdDAORequest);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse delete(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = CarAdMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.delete(carAdDAORequest);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.delete(id);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.deleteAll();
        return CarAdMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }

    @Override
    public CarAdDtoResponse get(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = CarAdMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(carAdDAORequest);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(id);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.getAll();
        return CarAdMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }

    @Override
    public CarAdDtoResponse update(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = CarAdMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.update(carAdDAORequest);
        return CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.getWithPagination(size, page);
        return CarAdMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }
}

package by.service;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.carAdMapper.DTODAOMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;

import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    @Override
    public CarAdDtoResponse add(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.add(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse delete(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.delete(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse delete(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.delete(id);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.deleteAll();
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }

    @Override
    public CarAdDtoResponse get(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(id);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.getAll();
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }

    @Override
    public CarAdDtoResponse update(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = DTODAOMapper.convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.update(carAdDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.getWithPagination(size, page);
        return DTODAOMapper.convertDAORespsToDTOResps(carAdDAORespons);
    }
}

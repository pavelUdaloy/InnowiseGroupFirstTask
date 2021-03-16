package by.mapper;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class CarAdMapper {

    public CarAdDtoResponse convertDAORespToDTOResp(CarAdDaoResponse carAdDAOResponse) {
        return setParamsToDTO(carAdDAOResponse);
    }

    public CarAdDaoRequest convertDTOReqToDAOReq(CarAdDtoRequest carAdDTORequest) {
        return setParamsToDao(carAdDTORequest);
    }

    public List<CarAdDaoRequest> convertDTOReqsToDAOReqs(List<CarAdDtoRequest> carAdDtoRequests) {
        List<CarAdDaoRequest> carAdDaoRequests = new ArrayList<>();
        for (CarAdDtoRequest carAdDTORequest : carAdDtoRequests) {
            carAdDaoRequests.add(setParamsToDao(carAdDTORequest));
        }
        return carAdDaoRequests;
    }

    public List<CarAdDtoResponse> convertDAORespsToDTOResps(List<CarAdDaoResponse> carAdDAORespons) {
        List<CarAdDtoResponse> carAdDtoRespons = new ArrayList<>();
        for (CarAdDaoResponse carAdDAOResponse : carAdDAORespons) {
            carAdDtoRespons.add(setParamsToDTO(carAdDAOResponse));
        }
        return carAdDtoRespons;
    }

    private CarAdDtoResponse setParamsToDTO(CarAdDaoResponse carAdDAOResponse) {
        CarAdDtoResponse carAdDTOResponse = new CarAdDtoResponse();
        carAdDTOResponse.setModel(carAdDAOResponse.getModel());
        carAdDTOResponse.setCreationDate(carAdDAOResponse.getCreationDate());
        carAdDTOResponse.setOwnerId(carAdDAOResponse.getOwnerId());
        carAdDTOResponse.setLastEditDate(carAdDAOResponse.getLastEditDate());
        carAdDTOResponse.setAge(carAdDAOResponse.getAge());
        carAdDTOResponse.setCondition(carAdDAOResponse.getCondition());
        carAdDTOResponse.setBrand(carAdDAOResponse.getBrand());
        carAdDTOResponse.setEnginePower(carAdDAOResponse.getEnginePower());
        carAdDTOResponse.setEngineSize(carAdDAOResponse.getEngineSize());
        carAdDTOResponse.setMileage(carAdDAOResponse.getMileage());
        carAdDTOResponse.setId(carAdDAOResponse.getId());
        return carAdDTOResponse;
    }

    private CarAdDaoRequest setParamsToDao(CarAdDtoRequest carAdDTORequest) {
        CarAdDaoRequest carAdDAORequest = new CarAdDaoRequest();
        carAdDAORequest.setId(carAdDTORequest.getId());
        carAdDAORequest.setModel(carAdDTORequest.getModel());
        carAdDAORequest.setCreationDate(carAdDTORequest.getCreationDate());
        carAdDAORequest.setOwnerId(carAdDTORequest.getOwnerId());
        carAdDAORequest.setLastEditDate(carAdDTORequest.getLastEditDate());
        carAdDAORequest.setAge(carAdDTORequest.getAge());
        carAdDAORequest.setCondition(carAdDTORequest.getCondition());
        carAdDAORequest.setBrand(carAdDTORequest.getBrand());
        carAdDAORequest.setEnginePower(carAdDTORequest.getEnginePower());
        carAdDAORequest.setEngineSize(carAdDTORequest.getEngineSize());
        carAdDAORequest.setMileage(carAdDTORequest.getMileage());
        return carAdDAORequest;
    }
}

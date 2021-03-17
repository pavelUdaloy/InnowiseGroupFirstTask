package by.mapper;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class CarAdMapper {

    public CarAdDtoResponse convertDaoResponseToDtoResponse(CarAdDaoResponse carAdDaoResponse) {
        return setParametersToDto(carAdDaoResponse);
    }

    public CarAdDaoRequest convertDtoRequestToDaoRequest(CarAdDtoRequest carAdDtoRequest) {
        return setParametersToDao(carAdDtoRequest);
    }

    public List<CarAdDaoRequest> convertDtoRequestsToDaoRequests(List<CarAdDtoRequest> carAdDtoRequests) {
        List<CarAdDaoRequest> carAdDaoRequests = new ArrayList<>();
        for (CarAdDtoRequest carAdDtoRequest : carAdDtoRequests) {
            carAdDaoRequests.add(setParametersToDao(carAdDtoRequest));
        }
        return carAdDaoRequests;
    }

    public List<CarAdDtoResponse> convertDaoResponsesToDtoResponses(List<CarAdDaoResponse> carAdDaoResponses) {
        List<CarAdDtoResponse> carAdDtoResponses = new ArrayList<>();
        for (CarAdDaoResponse carAdDAOResponse : carAdDaoResponses) {
            carAdDtoResponses.add(setParametersToDto(carAdDAOResponse));
        }
        return carAdDtoResponses;
    }

    private CarAdDtoResponse setParametersToDto(CarAdDaoResponse carAdDaoResponse) {
        CarAdDtoResponse carAdDtoResponse = new CarAdDtoResponse();
        carAdDtoResponse.setModel(carAdDaoResponse.getModel());
        carAdDtoResponse.setCreationDate(carAdDaoResponse.getCreationDate());
        carAdDtoResponse.setOwnerId(carAdDaoResponse.getOwnerId());
        carAdDtoResponse.setLastEditDate(carAdDaoResponse.getLastEditDate());
        carAdDtoResponse.setAge(carAdDaoResponse.getAge());
        carAdDtoResponse.setCondition(carAdDaoResponse.getCondition());
        carAdDtoResponse.setBrand(carAdDaoResponse.getBrand());
        carAdDtoResponse.setEnginePower(carAdDaoResponse.getEnginePower());
        carAdDtoResponse.setEngineSize(carAdDaoResponse.getEngineSize());
        carAdDtoResponse.setMileage(carAdDaoResponse.getMileage());
        carAdDtoResponse.setId(carAdDaoResponse.getId());
        return carAdDtoResponse;
    }

    private CarAdDaoRequest setParametersToDao(CarAdDtoRequest carAdDtoRequest) {
        CarAdDaoRequest carAdDaoRequest = new CarAdDaoRequest();
        carAdDaoRequest.setId(carAdDtoRequest.getId());
        carAdDaoRequest.setModel(carAdDtoRequest.getModel());
        carAdDaoRequest.setCreationDate(carAdDtoRequest.getCreationDate());
        carAdDaoRequest.setOwnerId(carAdDtoRequest.getOwnerId());
        carAdDaoRequest.setLastEditDate(carAdDtoRequest.getLastEditDate());
        carAdDaoRequest.setAge(carAdDtoRequest.getAge());
        carAdDaoRequest.setCondition(carAdDtoRequest.getCondition());
        carAdDaoRequest.setBrand(carAdDtoRequest.getBrand());
        carAdDaoRequest.setEnginePower(carAdDtoRequest.getEnginePower());
        carAdDaoRequest.setEngineSize(carAdDtoRequest.getEngineSize());
        carAdDaoRequest.setMileage(carAdDtoRequest.getMileage());
        return carAdDaoRequest;
    }
}

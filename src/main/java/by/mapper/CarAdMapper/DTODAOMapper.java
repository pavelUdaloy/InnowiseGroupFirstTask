package by.mapper.CarAdMapper;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;
import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.response.CarAdDTOResponse;

import java.util.ArrayList;
import java.util.List;

public class DTODAOMapper {

    public static CarAdDTOResponse convertDAORespToDTOResp(CarAdDAOResponse carAdDAOResponse) {
        return setParamsToDTO(carAdDAOResponse);
    }

    public static CarAdDAORequest convertDTOReqToDAOReq(CarAdDTORequest carAdDTORequest) {
        return setParamsToDao(carAdDTORequest);
    }

    public static List<CarAdDAORequest> convertDTOReqsToDAOReqs(List<CarAdDTORequest> carAdDTORequests) {
        List<CarAdDAORequest> carAdDAORequests = new ArrayList<>();
        for (CarAdDTORequest carAdDTORequest : carAdDTORequests) {
            carAdDAORequests.add(setParamsToDao(carAdDTORequest));
        }
        return carAdDAORequests;
    }

    public static List<CarAdDTOResponse> convertDAORespsToDTOResps(List<CarAdDAOResponse> carAdDAOResponses) {
        List<CarAdDTOResponse> carAdDTOResponses = new ArrayList<>();
        for (CarAdDAOResponse carAdDAOResponse : carAdDAOResponses) {
            carAdDTOResponses.add(setParamsToDTO(carAdDAOResponse));
        }
        return carAdDTOResponses;
    }

    private static CarAdDTOResponse setParamsToDTO(CarAdDAOResponse carAdDAOResponse) {
        CarAdDTOResponse carAdDTOResponse = new CarAdDTOResponse();
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

    private static CarAdDAORequest setParamsToDao(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = new CarAdDAORequest();
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

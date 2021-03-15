package by.mapper.CarAdMapper;

import by.entity.dao.request.CarAdDAORequest;
import by.entity.dao.response.CarAdDAOResponse;
import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.response.CarAdDTOResponse;

public class DTODAOMapper {
    public static CarAdDAORequest convertDTOReqToDAOReq(CarAdDTORequest carAdDTORequest) {
        CarAdDAORequest carAdDAORequest = new CarAdDAORequest();
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
    public static CarAdDAOResponse convertDAORespToDTOResp(CarAdDTOResponse carAdDTOResponse) {
        CarAdDAORequest carAdDAORequest = new CarAdDAORequest();
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

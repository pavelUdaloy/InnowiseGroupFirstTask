package by.service;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dao.response.UserDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.entity.dto.response.ImageDtoResponse;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.CarAdMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;
import by.servlet.CarAdServlet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    private final ImageService imageService = new ImageServiceImpl();
    private final TelephoneService telephoneService = new TelephoneServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    public CarAdDtoResponse add(CarAdDtoRequest carAdDTORequest, List<ImageDtoRequest> images) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = new CarAdMapper().convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.add(carAdDAORequest);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDAORespToDTOResp(carAdDaoResponse);

        for (ImageDtoRequest image : images) {
            image.setOwnerId(carAdDtoResponse.getId());
        }
        carAdDtoResponse.setImageQuantity(imageService.addAll(images).size());

        return carAdDtoResponse;
    }

    @Override
    public CarAdDtoResponse delete(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = new CarAdMapper().convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.delete(carAdDAORequest);
        return new CarAdMapper().convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdServlet.GetResponseBody delete(Integer carAdId, Integer userId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDaoResponse = carAdRepository.delete(carAdId);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDAORespToDTOResp(carAdDaoResponse);

        UserDaoResponse userDAOResponse = userService.get(userId);
        List<ImageDtoResponse> imageDtoResponses = imageService.get(carAdId);
        List<String> imagesIds = new ArrayList<>();
        for (ImageDtoResponse imageDTOResponse : imageDtoResponses) {
            imagesIds.add(String.valueOf(imageDTOResponse.getId()));
        }
        List<TelephoneDtoResponse> telephoneDtoResponses = telephoneService.get(carAdId);
        carAdDtoResponse.setTelephoneList(telephoneDtoResponses);
        carAdDtoResponse.setImageQuantity(imageDtoResponses.size());

        return new CarAdServlet.GetResponseBody(carAdDtoResponse, userDAOResponse, imagesIds);
    }

    @Override
    public List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAORespons = carAdRepository.deleteAll();
        return new CarAdMapper().convertDAORespsToDTOResps(carAdDAORespons);
    }

    @Override
    public CarAdDtoResponse get(CarAdDtoRequest carAdDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoRequest carAdDAORequest = new CarAdMapper().convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(carAdDAORequest);
        return new CarAdMapper().convertDAORespToDTOResp(carAdDAOResponse);
    }

    @Override
    public CarAdServlet.GetResponseBody get(Integer carAdId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(carAdId);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDAORespToDTOResp(carAdDAOResponse);

        UserDaoResponse userDAOResponse = userService.get(carAdDtoResponse.getOwnerId());

        List<ImageDtoResponse> imageDtoResponse = imageService.get(carAdDtoResponse.getId());
        carAdDtoResponse.setImageQuantity(imageDtoResponse.size());
        List<String> imagesIds = new ArrayList<>();
        for (ImageDtoResponse imageDTOResponse : imageDtoResponse) {
            imagesIds.add(String.valueOf(imageDTOResponse.getId()));
        }

        List<TelephoneDtoResponse> telephoneDtoResponse = telephoneService.get(carAdDtoResponse.getOwnerId());
        carAdDtoResponse.setTelephoneList(telephoneDtoResponse);

        return new CarAdServlet.GetResponseBody(carAdDtoResponse, userDAOResponse, imagesIds);
    }

    @Override
    public List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDAOResponse = carAdRepository.getAll();
        return new CarAdMapper().convertDAORespsToDTOResps(carAdDAOResponse);
    }

    @Override
    public CarAdDtoResponse update(Integer id, Integer age, String brand, String model, Integer engineSize, Integer enginePower, Integer mileage) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDtoRequest carAdDTORequest = new CarAdDtoRequest();

        carAdDTORequest.setId(id);
        carAdDTORequest.setAge(age);
        carAdDTORequest.setBrand(brand);
        carAdDTORequest.setModel(model);
        carAdDTORequest.setEngineSize(engineSize);
        carAdDTORequest.setEnginePower(enginePower);
        carAdDTORequest.setMileage(mileage);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        carAdDTORequest.setLastEditDate(timestamp);

        CarAdDaoRequest carAdDAORequest = new CarAdMapper().convertDTOReqToDAOReq(carAdDTORequest);
        CarAdDaoResponse carAdDAOResponse = carAdRepository.update(carAdDAORequest);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDAORespToDTOResp(carAdDAOResponse);

        List<ImageDtoResponse> imageDtoResponses = imageService.get(carAdDtoResponse.getId());
        carAdDtoResponse.setImageQuantity(imageDtoResponses.size());
        List<TelephoneDtoResponse> telephoneDtoResponses = telephoneService.get(carAdDtoResponse.getOwnerId());
        carAdDtoResponse.setTelephoneList(telephoneDtoResponses);

        return carAdDtoResponse;
    }

    @Override
    public List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<CarAdDaoResponse> carAdDaoResponse = carAdRepository.getWithPagination(size, page);
        List<CarAdDtoResponse> carAdDtoResponses = new CarAdMapper().convertDAORespsToDTOResps(carAdDaoResponse);
        for (CarAdDtoResponse carAdDtoResponse : carAdDtoResponses) {
            carAdDtoResponse.setImageQuantity(imageService.get(carAdDtoResponse.getId()).size());
            carAdDtoResponse.setTelephoneList(telephoneService.get(carAdDtoResponse.getOwnerId()));
        }
        return carAdDtoResponses;
    }
}

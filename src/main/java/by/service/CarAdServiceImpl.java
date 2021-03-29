package by.service;

import by.entity.base.User;
import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.entity.dto.response.ImageDtoResponse;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.mapper.CarAdMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;
import by.repository.UserRepository;
import by.repository.UserRepositoryImpl;
import by.servlet.CarAdServlet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    private final UserRepository userRepository  = new UserRepositoryImpl();

    private final ImageService imageService = new ImageServiceImpl();
    private final TelephoneService telephoneService = new TelephoneServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    public CarAdDtoResponse add(CarAdDtoRequest carAdDtoRequest, List<ImageDtoRequest> imageDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoRequest carAdDaoRequest = new CarAdMapper().convertDtoRequestToDaoRequest(carAdDtoRequest);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.add(carAdDaoRequest);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);

        for (ImageDtoRequest image : imageDtoRequests) {
            image.setOwnerId(carAdDtoResponse.getId());
        }
        carAdDtoResponse.setImageQuantity(imageService.addAll(imageDtoRequests).size());

        return carAdDtoResponse;
    }

    @Override
    public CarAdDtoResponse delete(CarAdDtoRequest carAdDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoRequest carAdDaoRequest = new CarAdMapper().convertDtoRequestToDaoRequest(carAdDtoRequest);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.delete(carAdDaoRequest);
        return new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);
    }

    @Override
    public CarAdServlet.ResponseBody delete(Integer carAdId, Integer userId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<ImageDtoResponse> imageDtoResponses = imageService.getByCarAdId(carAdId);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.delete(carAdId);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);

        User user = userRepository.get(userId);
        List<String> imagesIds = new ArrayList<>();
        for (ImageDtoResponse imageDtoResponse : imageDtoResponses) {
            imagesIds.add(String.valueOf(imageDtoResponse.getId()));
        }
        List<TelephoneDtoResponse> telephoneDtoResponses = telephoneService.get(userId);
        carAdDtoResponse.setTelephoneList(telephoneDtoResponses);
        carAdDtoResponse.setImageQuantity(imageDtoResponses.size());

        return new CarAdServlet.ResponseBody(carAdDtoResponse, user, imagesIds);
    }

    @Override
    public List<CarAdDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<CarAdDaoResponse> carAdDaoResponses = carAdRepository.deleteAll();
        return new CarAdMapper().convertDaoResponsesToDtoResponses(carAdDaoResponses);
    }

    @Override
    public CarAdDtoResponse get(CarAdDtoRequest carAdDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoRequest carAdDaoRequest = new CarAdMapper().convertDtoRequestToDaoRequest(carAdDtoRequest);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.get(carAdDaoRequest);
        return new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);
    }

    @Override
    public CarAdServlet.ResponseBody get(Integer carAdId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDaoResponse carAdDaoResponse = carAdRepository.get(carAdId);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);

        User user = userRepository.get(carAdDtoResponse.getOwnerId());

        List<ImageDtoResponse> imageDtoResponses = imageService.getByCarAdId(carAdDtoResponse.getId());
        carAdDtoResponse.setImageQuantity(imageDtoResponses.size());
        List<String> imagesIds = new ArrayList<>();
        for (ImageDtoResponse imageDtoResponse : imageDtoResponses) {
            imagesIds.add(String.valueOf(imageDtoResponse.getId()));
        }

        List<TelephoneDtoResponse> telephoneDtoResponse = telephoneService.get(carAdDtoResponse.getOwnerId());
        carAdDtoResponse.setTelephoneList(telephoneDtoResponse);

        return new CarAdServlet.ResponseBody(carAdDtoResponse, user, imagesIds);
    }

    @Override
    public List<CarAdDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<CarAdDaoResponse> carAdDaoResponses = carAdRepository.getAll();
        return new CarAdMapper().convertDaoResponsesToDtoResponses(carAdDaoResponses);
    }

    @Override
    public CarAdDtoResponse update(Integer id, Integer age, String brand, String model, Integer engineSize, Integer enginePower, Integer mileage) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        CarAdDtoRequest carAdDtoRequest = new CarAdDtoRequest();

        carAdDtoRequest.setId(id);
        carAdDtoRequest.setAge(age);
        carAdDtoRequest.setBrand(brand);
        carAdDtoRequest.setModel(model);
        carAdDtoRequest.setEngineSize(engineSize);
        carAdDtoRequest.setEnginePower(enginePower);
        carAdDtoRequest.setMileage(mileage);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        carAdDtoRequest.setLastEditDate(timestamp);

        CarAdDaoRequest carAdDaoRequest = new CarAdMapper().convertDtoRequestToDaoRequest(carAdDtoRequest);
        CarAdDaoResponse carAdDaoResponse = carAdRepository.update(carAdDaoRequest);
        CarAdDtoResponse carAdDtoResponse = new CarAdMapper().convertDaoResponseToDtoResponse(carAdDaoResponse);

        List<ImageDtoResponse> imageDtoResponses = imageService.getByCarAdId(carAdDtoResponse.getId());
        carAdDtoResponse.setImageQuantity(imageDtoResponses.size());
        List<TelephoneDtoResponse> telephoneDtoResponses = telephoneService.get(carAdDtoResponse.getOwnerId());
        carAdDtoResponse.setTelephoneList(telephoneDtoResponses);

        return carAdDtoResponse;
    }

    @Override
    public List<CarAdDtoResponse> getWithPagination(Integer size, Integer page) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<CarAdDaoResponse> carAdDaoResponse = carAdRepository.getWithPagination(size, page);
        List<CarAdDtoResponse> carAdDtoResponses = new CarAdMapper().convertDaoResponsesToDtoResponses(carAdDaoResponse);
        for (CarAdDtoResponse carAdDtoResponse : carAdDtoResponses) {
            carAdDtoResponse.setImageQuantity(imageService.getByCarAdId(carAdDtoResponse.getId()).size());
            carAdDtoResponse.setTelephoneList(telephoneService.get(carAdDtoResponse.getOwnerId()));
        }
        return carAdDtoResponses;
    }
}

package by.service;

import by.entity.dao.request.CarAdDaoRequest;
import by.entity.dao.response.CarAdDaoResponse;
import by.entity.dao.response.UserDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.entity.dto.response.ImageDtoResponse;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.CarAdMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;
import by.servlet.CarAdServlet;

import java.util.ArrayList;
import java.util.List;

public class CarAdServiceImpl implements CarAdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    private final ImageService imageService = new ImageServiceImpl();
    private final TelephoneService telephoneService = new TelephoneServiceImpl();
    private final UserService userService = new UserServiceImpl();

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
    public CarAdServlet.GetResponseBody get(Integer carAdId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        CarAdDaoResponse carAdDAOResponse = carAdRepository.get(carAdId);
        CarAdDtoResponse carAdDtoResponse = CarAdMapper.convertDAORespToDTOResp(carAdDAOResponse);

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
        List<CarAdDaoResponse> carAdDaoResponse = carAdRepository.getWithPagination(size, page);
        List<CarAdDtoResponse> carAdDtoResponses = CarAdMapper.convertDAORespsToDTOResps(carAdDaoResponse);
        for (CarAdDtoResponse carAdDtoResponse : carAdDtoResponses) {
            carAdDtoResponse.setImageQuantity(imageService.get(carAdDtoResponse.getId()).size());
            carAdDtoResponse.setTelephoneList(telephoneService.get(carAdDtoResponse.getOwnerId()));
        }
        return carAdDtoResponses;
    }
}

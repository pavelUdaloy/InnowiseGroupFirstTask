package by.service;

import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.mapper.ImageMapper;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();

    @Override
    public List<ImageDtoResponse> addAll(List<ImageDtoRequest> imageDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<ImageDaoRequest> imageDaoRequests = new ImageMapper().convertDtoRequestsToDaoRequests(imageDtoRequests);
        List<ImageDaoResponse> imageDaoResponse = imageRepository.addAll(imageDaoRequests);
        return new ImageMapper().convertDaoResponsesToDtoResponses(imageDaoResponse);
    }

    @Override
    public ImageDtoResponse delete(ImageDtoRequest imageDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        ImageDaoRequest imageDaoRequest = new ImageMapper().convertDtoRequestToDaoRequest(imageDtoRequest);
        ImageDaoResponse imageDaoResponse = imageRepository.delete(imageDaoRequest);
        return new ImageMapper().convertDaoResponseToDtoResponse(imageDaoResponse);
    }

    @Override
    public List<ImageDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<ImageDaoResponse> imageDaoResponses = imageRepository.deleteAll();
        return new ImageMapper().convertDaoResponsesToDtoResponses(imageDaoResponses);
    }

    @Override
    public ImageDtoResponse get(ImageDtoRequest imageDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        ImageDaoRequest imageDaoRequest = new ImageMapper().convertDtoRequestToDaoRequest(imageDtoRequest);
        ImageDaoResponse imageDaoResponse = imageRepository.get(imageDaoRequest);
        return new ImageMapper().convertDaoResponseToDtoResponse(imageDaoResponse);
    }

    @Override
    public List<ImageDtoResponse> getByCarAdId(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<ImageDaoResponse> imageDaoResponses = imageRepository.getByOwnerId(ownerId);
        return new ImageMapper().convertDaoResponsesToDtoResponses(imageDaoResponses);
    }

    @Override
    public ImageDtoResponse get(Integer id) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        ImageDaoResponse imageDaoResponse = imageRepository.get(id);
        return new ImageMapper().convertDaoResponseToDtoResponse(imageDaoResponse);
    }

    @Override
    public List<ImageDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<ImageDaoResponse> imageDaoResponse = imageRepository.getAll();
        return new ImageMapper().convertDaoResponsesToDtoResponses(imageDaoResponse);
    }
}

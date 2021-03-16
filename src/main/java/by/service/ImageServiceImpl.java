package by.service;

import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.ImageMapper;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();

    @Override
    public List<ImageDtoResponse> addAll(List<ImageDtoRequest> images) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<ImageDaoRequest> imageDaoRequests = new ImageMapper().convertDTOReqsToDAOReqs(images);
        List<ImageDaoResponse> imageDaoResponse = imageRepository.addAll(imageDaoRequests);
        return new ImageMapper().convertDAORespsToDTOResps(imageDaoResponse);
    }

    @Override
    public ImageDtoResponse delete(ImageDtoRequest imageDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        ImageDaoRequest imageDAORequest = new ImageMapper().convertDTOReqToDAOReq(imageDTORequest);
        ImageDaoResponse imageDAOResponse = imageRepository.delete(imageDAORequest);
        return new ImageMapper().convertDAORespToDTOResp(imageDAOResponse);
    }

    @Override
    public List<ImageDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<ImageDaoResponse> imageDaoRespons = imageRepository.deleteAll();
        return new ImageMapper().convertDAORespsToDTOResps(imageDaoRespons);
    }

    @Override
    public ImageDtoResponse get(ImageDtoRequest imageDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        ImageDaoRequest imageDAORequest = new ImageMapper().convertDTOReqToDAOReq(imageDTORequest);
        ImageDaoResponse imageDAOResponse = imageRepository.get(imageDAORequest);
        return new ImageMapper().convertDAORespToDTOResp(imageDAOResponse);
    }

    @Override
    public List<ImageDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<ImageDaoResponse> imageDaoResponse = imageRepository.get(ownerId);
        return new ImageMapper().convertDAORespsToDTOResps(imageDaoResponse);
    }

    @Override
    public List<ImageDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<ImageDaoResponse> imageDaoResponse = imageRepository.getAll();
        return new ImageMapper().convertDAORespsToDTOResps(imageDaoResponse);
    }
}

package by.service;

import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;
import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.ImageDTOResponse;
import by.mapper.ImageMapper.DTODAOMapper;
import by.repository.ImageRepository;
import by.repository.ImageRepositoryImpl;

import java.util.List;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository = new ImageRepositoryImpl();

    @Override
    public List<ImageDTOResponse> addAll(List<ImageDTORequest> images) {
        List<ImageDAORequest> imageDAORequests = DTODAOMapper.convertDTOReqsToDAOReqs(images);
        List<ImageDAOResponse> imageDAOResponses = imageRepository.addAll(imageDAORequests);
        return DTODAOMapper.convertDAORespsToDTOResps(imageDAOResponses);
    }

    @Override
    public ImageDTOResponse delete(ImageDTORequest imageDTORequest) {
        ImageDAORequest imageDAORequest = DTODAOMapper.convertDTOReqToDAOReq(imageDTORequest);
        ImageDAOResponse imageDAOResponse = imageRepository.delete(imageDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(imageDAOResponse);
    }

    @Override
    public List<ImageDTOResponse> deleteAll() {
        List<ImageDAOResponse> imageDAOResponses = imageRepository.deleteAll();
        return DTODAOMapper.convertDAORespsToDTOResps(imageDAOResponses);
    }

    @Override
    public ImageDTOResponse get(ImageDTORequest imageDTORequest) {
        ImageDAORequest imageDAORequest = DTODAOMapper.convertDTOReqToDAOReq(imageDTORequest);
        ImageDAOResponse imageDAOResponse = imageRepository.get(imageDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(imageDAOResponse);
    }

    @Override
    public List<ImageDTOResponse> getByOwnerId(Integer ownerId) {
        List<ImageDAOResponse> imageDAOResponses = imageRepository.getByOwnerId(ownerId);
        return DTODAOMapper.convertDAORespsToDTOResps(imageDAOResponses);
    }

    @Override
    public List<ImageDTOResponse> getAll() {
        List<ImageDAOResponse> imageDAOResponses = imageRepository.getAll();
        return DTODAOMapper.convertDAORespsToDTOResps(imageDAOResponses);
    }
}

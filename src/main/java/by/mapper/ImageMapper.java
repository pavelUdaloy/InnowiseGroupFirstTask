package by.mapper;


import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class ImageMapper {
    public List<ImageDaoRequest> convertDtoRequestsToDaoRequests(List<ImageDtoRequest> imageDtoRequests) {
        List<ImageDaoRequest> imageDaoRequests = new ArrayList<>();
        for (ImageDtoRequest imageDtoRequest : imageDtoRequests) {
            ImageDaoRequest imageDaoRequest = new ImageDaoRequest();
            imageDaoRequest.setFileFormat(imageDtoRequest.getFileFormat());
            imageDaoRequest.setName(imageDtoRequest.getName());
            imageDaoRequest.setOwnerId(imageDtoRequest.getOwnerId());
            imageDaoRequests.add(imageDaoRequest);
        }
        return imageDaoRequests;
    }

    public List<ImageDtoResponse> convertDaoResponsesToDtoResponses(List<ImageDaoResponse> imageDaoResponses) {
        List<ImageDtoResponse> imageDtoResponses = new ArrayList<>();
        for (ImageDaoResponse imageDaoResponse : imageDaoResponses) {
            ImageDtoResponse imageDtoResponse = new ImageDtoResponse();
            imageDtoResponse.setFileFormat(imageDaoResponse.getFileFormat());
            imageDtoResponse.setName(imageDaoResponse.getName());
            imageDtoResponse.setOwnerId(imageDaoResponse.getOwnerId());
            imageDtoResponse.setId(imageDaoResponse.getId());
            imageDtoResponses.add(imageDtoResponse);
        }
        return imageDtoResponses;
    }

    public ImageDaoRequest convertDtoRequestToDaoRequest(ImageDtoRequest imageDtoRequest) {
        ImageDaoRequest imageDaoRequest = new ImageDaoRequest();
        imageDaoRequest.setFileFormat(imageDtoRequest.getFileFormat());
        imageDaoRequest.setName(imageDtoRequest.getName());
        imageDaoRequest.setOwnerId(imageDtoRequest.getOwnerId());
        return imageDaoRequest;
    }

    public ImageDtoResponse convertDaoResponseToDtoResponse(ImageDaoResponse imageDaoResponse) {
        ImageDtoResponse imageDtoResponse = new ImageDtoResponse();
        imageDtoResponse.setFileFormat(imageDaoResponse.getFileFormat());
        imageDtoResponse.setName(imageDaoResponse.getName());
        imageDtoResponse.setOwnerId(imageDaoResponse.getOwnerId());
        imageDtoResponse.setId(imageDaoResponse.getId());
        return imageDtoResponse;
    }
}

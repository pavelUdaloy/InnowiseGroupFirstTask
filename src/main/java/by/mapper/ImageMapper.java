package by.mapper;


import by.entity.dao.request.ImageDaoRequest;
import by.entity.dao.response.ImageDaoResponse;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.ImageDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class ImageMapper {
    public static List<ImageDaoRequest> convertDTOReqsToDAOReqs(List<ImageDtoRequest> imageDtoRequests) {
        List<ImageDaoRequest> imageDaoRequests = new ArrayList<>();
        for (ImageDtoRequest imageDTORequest : imageDtoRequests) {
            ImageDaoRequest imageDAORequest = new ImageDaoRequest();
            imageDAORequest.setFileFormat(imageDTORequest.getFileFormat());
            imageDAORequest.setName(imageDTORequest.getName());
            imageDAORequest.setOwnerId(imageDTORequest.getOwnerId());
            imageDaoRequests.add(imageDAORequest);
        }
        return imageDaoRequests;
    }

    public static List<ImageDtoResponse> convertDAORespsToDTOResps(List<ImageDaoResponse> imageDaoRespons) {
        List<ImageDtoResponse> imageDtoRespons = new ArrayList<>();
        for (ImageDaoResponse imageDAOResponse : imageDaoRespons) {
            ImageDtoResponse imageDTOResponse = new ImageDtoResponse();
            imageDTOResponse.setFileFormat(imageDAOResponse.getFileFormat());
            imageDTOResponse.setName(imageDAOResponse.getName());
            imageDTOResponse.setOwnerId(imageDAOResponse.getOwnerId());
            imageDTOResponse.setId(imageDAOResponse.getId());
            imageDtoRespons.add(imageDTOResponse);
        }
        return imageDtoRespons;
    }

    public static ImageDaoRequest convertDTOReqToDAOReq(ImageDtoRequest imageDTORequest) {
        ImageDaoRequest imageDAORequest = new ImageDaoRequest();
        imageDAORequest.setFileFormat(imageDTORequest.getFileFormat());
        imageDAORequest.setName(imageDTORequest.getName());
        imageDAORequest.setOwnerId(imageDTORequest.getOwnerId());
        return imageDAORequest;
    }

    public static ImageDtoResponse convertDAORespToDTOResp(ImageDaoResponse imageDAOResponse) {
        ImageDtoResponse imageDTOResponse = new ImageDtoResponse();
        imageDTOResponse.setFileFormat(imageDAOResponse.getFileFormat());
        imageDTOResponse.setName(imageDAOResponse.getName());
        imageDTOResponse.setOwnerId(imageDAOResponse.getOwnerId());
        imageDTOResponse.setId(imageDAOResponse.getId());
        return imageDTOResponse;
    }
}

package by.mapper.ImageMapper;


import by.entity.dao.request.ImageDAORequest;
import by.entity.dao.response.ImageDAOResponse;
import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.ImageDTOResponse;

import java.util.ArrayList;
import java.util.List;

public class DTODAOMapper {
    public static List<ImageDAORequest> convertDTOReqsToDAOReqs(List<ImageDTORequest> imageDTORequests) {
        List<ImageDAORequest> imageDAORequests = new ArrayList<>();
        for (ImageDTORequest imageDTORequest : imageDTORequests) {
            ImageDAORequest imageDAORequest = new ImageDAORequest();
            imageDAORequest.setFileFormat(imageDTORequest.getFileFormat());
            imageDAORequest.setName(imageDTORequest.getName());
            imageDAORequest.setOwnerId(imageDTORequest.getOwnerId());
            imageDAORequests.add(imageDAORequest);
        }
        return imageDAORequests;
    }

    public static List<ImageDTOResponse> convertDAORespsToDTOResps(List<ImageDAOResponse> imageDAOResponses) {
        List<ImageDTOResponse> imageDTOResponses = new ArrayList<>();
        for (ImageDAOResponse imageDAOResponse : imageDAOResponses) {
            ImageDTOResponse imageDTOResponse = new ImageDTOResponse();
            imageDTOResponse.setFileFormat(imageDAOResponse.getFileFormat());
            imageDTOResponse.setName(imageDAOResponse.getName());
            imageDTOResponse.setOwnerId(imageDAOResponse.getOwnerId());
            imageDTOResponse.setId(imageDAOResponse.getId());
            imageDTOResponses.add(imageDTOResponse);
        }
        return imageDTOResponses;
    }

    public static ImageDAORequest convertDTOReqToDAOReq(ImageDTORequest imageDTORequest) {
        ImageDAORequest imageDAORequest = new ImageDAORequest();
        imageDAORequest.setFileFormat(imageDTORequest.getFileFormat());
        imageDAORequest.setName(imageDTORequest.getName());
        imageDAORequest.setOwnerId(imageDTORequest.getOwnerId());
        return imageDAORequest;
    }

    public static ImageDTOResponse convertDAORespToDTOResp(ImageDAOResponse imageDAOResponse) {
        ImageDTOResponse imageDTOResponse = new ImageDTOResponse();
        imageDTOResponse.setFileFormat(imageDAOResponse.getFileFormat());
        imageDTOResponse.setName(imageDAOResponse.getName());
        imageDTOResponse.setOwnerId(imageDAOResponse.getOwnerId());
        imageDTOResponse.setId(imageDAOResponse.getId());
        return imageDTOResponse;
    }
}

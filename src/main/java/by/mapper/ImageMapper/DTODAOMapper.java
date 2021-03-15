package by.mapper.ImageMapper;


import by.entity.dao.request.ImageDAORequest;
import by.entity.dto.request.ImageDTORequest;

import java.util.ArrayList;
import java.util.List;

public class DTODAOMapper {
    public static List<ImageDAORequest> convertDTOReqToDAOReq(List<ImageDTORequest> imageDTORequests) {
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
}

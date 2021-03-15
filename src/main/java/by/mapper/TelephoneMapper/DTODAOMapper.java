package by.mapper.TelephoneMapper;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;
import by.entity.dto.request.TelephoneDTORequest;
import by.entity.dto.response.TelephoneDTOResponse;

import java.util.ArrayList;
import java.util.List;

public class DTODAOMapper {
    public static List<TelephoneDAORequest> convertDTOReqsToDAOReqs(List<TelephoneDTORequest> telephoneDTORequestList) {
        List<TelephoneDAORequest> telephoneDAORequests = new ArrayList<>();
        for (TelephoneDTORequest telephoneDTORequest : telephoneDTORequestList) {
            TelephoneDAORequest telephoneDAORequest = new TelephoneDAORequest();
            telephoneDAORequest.setNumber(telephoneDTORequest.getNumber());
            telephoneDAORequest.setOwnerId(telephoneDTORequest.getOwnerId());
            telephoneDAORequests.add(telephoneDAORequest);
        }
        return telephoneDAORequests;
    }

    public static List<TelephoneDTOResponse> convertDAORespsToDTOResps(List<TelephoneDAOResponse> telephoneDAOResponses) {
        List<TelephoneDTOResponse> telephoneDAORequests = new ArrayList<>();
        for (TelephoneDAOResponse telephoneDAOResponse : telephoneDAOResponses) {
            TelephoneDTOResponse telephoneDTOResponse = new TelephoneDTOResponse();
            telephoneDTOResponse.setNumber(telephoneDAOResponse.getNumber());
            telephoneDTOResponse.setId(telephoneDAOResponse.getId());
            telephoneDTOResponse.setOwnerId(telephoneDAOResponse.getOwnerId());
            telephoneDAORequests.add(telephoneDTOResponse);
        }
        return telephoneDAORequests;
    }

    public static TelephoneDAORequest convertDTOReqToDAOReq(TelephoneDTORequest telephoneDTORequest) {
        TelephoneDAORequest telephoneDAORequest = new TelephoneDAORequest();
        telephoneDAORequest.setNumber(telephoneDTORequest.getNumber());
        telephoneDAORequest.setOwnerId(telephoneDTORequest.getOwnerId());
        return telephoneDAORequest;
    }

    public static TelephoneDTOResponse convertDAORespToDTOResp(TelephoneDAOResponse telephoneDAOResponse) {
        TelephoneDTOResponse telephoneDTOResponse = new TelephoneDTOResponse();
        telephoneDTOResponse.setNumber(telephoneDAOResponse.getNumber());
        telephoneDTOResponse.setOwnerId(telephoneDAOResponse.getOwnerId());
        telephoneDTOResponse.setId(telephoneDAOResponse.getId());
        return telephoneDTOResponse;
    }
}

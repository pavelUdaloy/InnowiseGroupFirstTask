package by.mapper;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class TelephoneMapper {
    public static List<TelephoneDaoRequest> convertDTOReqsToDAOReqs(List<TelephoneDtoRequest> telephoneDtoRequestList) {
        List<TelephoneDaoRequest> telephoneDaoRequests = new ArrayList<>();
        for (TelephoneDtoRequest telephoneDTORequest : telephoneDtoRequestList) {
            TelephoneDaoRequest telephoneDAORequest = new TelephoneDaoRequest();
            telephoneDAORequest.setNumber(telephoneDTORequest.getNumber());
            telephoneDAORequest.setOwnerId(telephoneDTORequest.getOwnerId());
            telephoneDaoRequests.add(telephoneDAORequest);
        }
        return telephoneDaoRequests;
    }

    public static List<TelephoneDtoResponse> convertDAORespsToDTOResps(List<TelephoneDaoResponse> telephoneDaoRespons) {
        List<TelephoneDtoResponse> telephoneDAORequests = new ArrayList<>();
        for (TelephoneDaoResponse telephoneDAOResponse : telephoneDaoRespons) {
            TelephoneDtoResponse telephoneDTOResponse = new TelephoneDtoResponse();
            telephoneDTOResponse.setNumber(telephoneDAOResponse.getNumber());
            telephoneDTOResponse.setId(telephoneDAOResponse.getId());
            telephoneDTOResponse.setOwnerId(telephoneDAOResponse.getOwnerId());
            telephoneDAORequests.add(telephoneDTOResponse);
        }
        return telephoneDAORequests;
    }

    public static TelephoneDaoRequest convertDTOReqToDAOReq(TelephoneDtoRequest telephoneDTORequest) {
        TelephoneDaoRequest telephoneDAORequest = new TelephoneDaoRequest();
        telephoneDAORequest.setNumber(telephoneDTORequest.getNumber());
        telephoneDAORequest.setOwnerId(telephoneDTORequest.getOwnerId());
        return telephoneDAORequest;
    }

    public static TelephoneDtoResponse convertDAORespToDTOResp(TelephoneDaoResponse telephoneDAOResponse) {
        TelephoneDtoResponse telephoneDTOResponse = new TelephoneDtoResponse();
        telephoneDTOResponse.setNumber(telephoneDAOResponse.getNumber());
        telephoneDTOResponse.setOwnerId(telephoneDAOResponse.getOwnerId());
        telephoneDTOResponse.setId(telephoneDAOResponse.getId());
        return telephoneDTOResponse;
    }
}

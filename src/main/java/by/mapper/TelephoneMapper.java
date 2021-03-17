package by.mapper;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class TelephoneMapper {

    public List<TelephoneDaoRequest> convertDtoRequestsToDAORequests(List<TelephoneDtoRequest> telephoneDtoRequests) {
        List<TelephoneDaoRequest> telephoneDaoRequests = new ArrayList<>();
        for (TelephoneDtoRequest telephoneDtoRequest : telephoneDtoRequests) {
            TelephoneDaoRequest telephoneDaoRequest = new TelephoneDaoRequest();
            telephoneDaoRequest.setNumber(telephoneDtoRequest.getNumber());
            telephoneDaoRequest.setOwnerId(telephoneDtoRequest.getOwnerId());
            telephoneDaoRequests.add(telephoneDaoRequest);
        }
        return telephoneDaoRequests;
    }

    public List<TelephoneDtoResponse> convertDaoResponsesToDtoResponses(List<TelephoneDaoResponse> telephoneDaoResponses) {
        List<TelephoneDtoResponse> telephoneDtoResponses = new ArrayList<>();
        for (TelephoneDaoResponse telephoneDaoResponse : telephoneDaoResponses) {
            TelephoneDtoResponse telephoneDtoResponse = new TelephoneDtoResponse();
            telephoneDtoResponse.setNumber(telephoneDaoResponse.getNumber());
            telephoneDtoResponse.setId(telephoneDaoResponse.getId());
            telephoneDtoResponse.setOwnerId(telephoneDaoResponse.getOwnerId());
            telephoneDtoResponses.add(telephoneDtoResponse);
        }
        return telephoneDtoResponses;
    }

    public TelephoneDaoRequest convertDtoRequestToDaoRequest(TelephoneDtoRequest telephoneDtoRequest) {
        TelephoneDaoRequest telephoneDaoRequest = new TelephoneDaoRequest();
        telephoneDaoRequest.setNumber(telephoneDtoRequest.getNumber());
        telephoneDaoRequest.setOwnerId(telephoneDtoRequest.getOwnerId());
        return telephoneDaoRequest;
    }

    public TelephoneDtoResponse convertDaoResponseToDtoResponse(TelephoneDaoResponse telephoneDaoResponse) {
        TelephoneDtoResponse telephoneDtoResponse = new TelephoneDtoResponse();
        telephoneDtoResponse.setNumber(telephoneDaoResponse.getNumber());
        telephoneDtoResponse.setOwnerId(telephoneDaoResponse.getOwnerId());
        telephoneDtoResponse.setId(telephoneDaoResponse.getId());
        return telephoneDtoResponse;
    }
}

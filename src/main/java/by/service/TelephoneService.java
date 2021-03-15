package by.service;

import by.entity.dto.request.TelephoneDTORequest;
import by.entity.dto.response.TelephoneDTOResponse;

import java.util.List;

public interface TelephoneService {
    List<TelephoneDTOResponse> addAll(List<TelephoneDTORequest> telephoneDTORequests);

    TelephoneDTOResponse delete(TelephoneDTORequest telephoneDTORequest);

    List<TelephoneDTOResponse> deleteAll();

    TelephoneDTOResponse get(TelephoneDTORequest telephoneDTORequest);

    List<TelephoneDTOResponse> getByOwnerId(Integer ownerId);

    List<TelephoneDTOResponse> getAll();

    TelephoneDTOResponse set(TelephoneDTORequest telephoneDTORequest, String newNumber);
}

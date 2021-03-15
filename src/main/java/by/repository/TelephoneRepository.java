package by.repository;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;

import java.util.List;

public interface TelephoneRepository {
    List<TelephoneDAOResponse> addAll(List<TelephoneDAORequest> telephoneDAORequests);

    TelephoneDAOResponse delete(TelephoneDAORequest telephoneDAORequest);

    List<TelephoneDAOResponse> deleteAll();

    TelephoneDAOResponse get(TelephoneDAORequest telephoneDAORequest);

    List<TelephoneDAOResponse> getByOwnerId(Integer ownerId);

    List<TelephoneDAOResponse> getAll();

    TelephoneDAOResponse set(TelephoneDAORequest telephoneDAORequest, String newNumber);
}

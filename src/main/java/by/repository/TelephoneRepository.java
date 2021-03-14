package by.repository;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;

import java.util.List;

public interface TelephoneRepository {
    TelephoneDAOResponse add(TelephoneDAORequest telephoneDAORequest);

    TelephoneDAOResponse delete(TelephoneDAORequest telephoneDAORequest);

    List<TelephoneDAOResponse> deleteAll();

    TelephoneDAOResponse get(TelephoneDAORequest telephoneDAORequest);

    List<TelephoneDAOResponse> getAll();

    TelephoneDAOResponse set(TelephoneDAORequest telephoneDAORequest, String newNumber);
}

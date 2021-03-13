package by.repository;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;

import java.util.List;

public interface TelephoneRepository {
    TelephoneDAOResponse add(TelephoneDAORequest image);

    TelephoneDAOResponse delete(TelephoneDAORequest image);

    List<TelephoneDAOResponse> deleteAll();

    TelephoneDAOResponse get(TelephoneDAORequest image);

    List<TelephoneDAOResponse> getAll();

    List<TelephoneDAOResponse> set(TelephoneDAORequest image);
}

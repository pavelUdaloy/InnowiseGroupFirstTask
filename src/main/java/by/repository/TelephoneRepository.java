package by.repository;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface TelephoneRepository {
    List<TelephoneDaoResponse> addAll(List<TelephoneDaoRequest> telephoneDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDaoResponse delete(TelephoneDaoRequest telephoneDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDaoResponse get(TelephoneDaoRequest telephoneDAORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDaoResponse update(TelephoneDaoRequest telephoneDAORequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

package by.repository;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface TelephoneRepository {
    List<TelephoneDaoResponse> addAll(List<TelephoneDaoRequest> telephoneDaoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDaoResponse delete(TelephoneDaoRequest telephoneDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDaoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDaoResponse get(TelephoneDaoRequest telephoneDaoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDaoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDaoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDaoResponse update(TelephoneDaoRequest telephoneDaoRequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

package by.service;

import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;

import java.util.List;

public interface TelephoneService {
    List<TelephoneDtoResponse> addAll(List<TelephoneDtoRequest> telephoneDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDtoResponse delete(TelephoneDtoRequest telephoneDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDtoResponse get(TelephoneDtoRequest telephoneDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    List<TelephoneDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;

    TelephoneDtoResponse update(TelephoneDtoRequest telephoneDtoRequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException;
}

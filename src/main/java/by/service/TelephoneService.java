package by.service;

import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;

import java.util.List;

public interface TelephoneService {
    List<TelephoneDtoResponse> addAll(List<TelephoneDtoRequest> telephoneDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDtoResponse delete(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDtoResponse get(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    List<TelephoneDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException;

    TelephoneDtoResponse update(TelephoneDtoRequest telephoneDTORequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException;
}

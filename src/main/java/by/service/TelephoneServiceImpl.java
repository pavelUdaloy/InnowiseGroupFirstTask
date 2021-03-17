package by.service;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.exception.NullQueryException;
import by.mapper.TelephoneMapper;
import by.repository.TelephoneRepository;
import by.repository.TelephoneRepositoryImpl;

import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository = new TelephoneRepositoryImpl();

    @Override
    public List<TelephoneDtoResponse> addAll(List<TelephoneDtoRequest> telephoneDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<TelephoneDaoRequest> telephoneDaoRequests = new TelephoneMapper().convertDtoRequestsToDAORequests(telephoneDtoRequests);
        List<TelephoneDaoResponse> telephoneDaoResponses = telephoneRepository.addAll(telephoneDaoRequests);
        return new TelephoneMapper().convertDaoResponsesToDtoResponses(telephoneDaoResponses);
    }

    @Override
    public TelephoneDtoResponse delete(TelephoneDtoRequest telephoneDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        TelephoneDaoRequest telephoneDaoRequest = new TelephoneMapper().convertDtoRequestToDaoRequest(telephoneDtoRequest);
        TelephoneDaoResponse telephoneDaoResponse = telephoneRepository.delete(telephoneDaoRequest);
        return new TelephoneMapper().convertDaoResponseToDtoResponse(telephoneDaoResponse);
    }

    @Override
    public List<TelephoneDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<TelephoneDaoResponse> telephoneDaoResponses = telephoneRepository.deleteAll();
        return new TelephoneMapper().convertDaoResponsesToDtoResponses(telephoneDaoResponses);
    }

    @Override
    public TelephoneDtoResponse get(TelephoneDtoRequest telephoneDtoRequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        TelephoneDaoRequest telephoneDaoRequest = new TelephoneMapper().convertDtoRequestToDaoRequest(telephoneDtoRequest);
        TelephoneDaoResponse telephoneDaoResponse = telephoneRepository.get(telephoneDaoRequest);
        return new TelephoneMapper().convertDaoResponseToDtoResponse(telephoneDaoResponse);
    }

    @Override
    public List<TelephoneDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<TelephoneDaoResponse> telephoneDaoResponses = telephoneRepository.get(ownerId);
        return new TelephoneMapper().convertDaoResponsesToDtoResponses(telephoneDaoResponses);
    }

    @Override
    public List<TelephoneDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        List<TelephoneDaoResponse> telephoneDaoResponses = telephoneRepository.getAll();
        return new TelephoneMapper().convertDaoResponsesToDtoResponses(telephoneDaoResponses);
    }

    @Override
    public TelephoneDtoResponse update(TelephoneDtoRequest telephoneDtoRequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException, NullQueryException {
        TelephoneDaoRequest telephoneDaoRequest = new TelephoneMapper().convertDtoRequestToDaoRequest(telephoneDtoRequest);
        TelephoneDaoResponse telephoneDaoResponse = telephoneRepository.update(telephoneDaoRequest, newNumber);
        return new TelephoneMapper().convertDaoResponseToDtoResponse(telephoneDaoResponse);
    }
}

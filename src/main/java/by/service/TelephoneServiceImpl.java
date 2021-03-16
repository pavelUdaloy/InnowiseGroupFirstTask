package by.service;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.TelephoneMapper;
import by.repository.TelephoneRepository;
import by.repository.TelephoneRepositoryImpl;

import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository = new TelephoneRepositoryImpl();

    @Override
    public List<TelephoneDtoResponse> addAll(List<TelephoneDtoRequest> telephoneDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoRequest> telephoneDaoRequests = TelephoneMapper.convertDTOReqsToDAOReqs(telephoneDtoRequests);
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.addAll(telephoneDaoRequests);
        return TelephoneMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse delete(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = TelephoneMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.delete(telephoneDAORequest);
        return TelephoneMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.deleteAll();
        return TelephoneMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse get(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = TelephoneMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.get(telephoneDAORequest);
        return TelephoneMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDtoResponse> get(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.get(ownerId);
        return TelephoneMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public List<TelephoneDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.getAll();
        return TelephoneMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse update(TelephoneDtoRequest telephoneDTORequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = TelephoneMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.update(telephoneDAORequest, newNumber);
        return TelephoneMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }
}

package by.service;

import by.entity.dao.request.TelephoneDaoRequest;
import by.entity.dao.response.TelephoneDaoResponse;
import by.entity.dto.request.TelephoneDtoRequest;
import by.entity.dto.response.TelephoneDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.mapper.TelephoneMapper.DTODAOMapper;
import by.repository.TelephoneRepository;
import by.repository.TelephoneRepositoryImpl;

import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository = new TelephoneRepositoryImpl();

    @Override
    public List<TelephoneDtoResponse> addAll(List<TelephoneDtoRequest> telephoneDtoRequests) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoRequest> telephoneDaoRequests = DTODAOMapper.convertDTOReqsToDAOReqs(telephoneDtoRequests);
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.addAll(telephoneDaoRequests);
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse delete(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.delete(telephoneDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDtoResponse> deleteAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.deleteAll();
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse get(TelephoneDtoRequest telephoneDTORequest) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.get(telephoneDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDtoResponse> getByOwnerId(Integer ownerId) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.getByOwnerId(ownerId);
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public List<TelephoneDtoResponse> getAll() throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        List<TelephoneDaoResponse> telephoneDaoRespons = telephoneRepository.getAll();
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDaoRespons);
    }

    @Override
    public TelephoneDtoResponse update(TelephoneDtoRequest telephoneDTORequest, String newNumber) throws IncorrectSQLParametersException, ConnectionWithDBLostException {
        TelephoneDaoRequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDaoResponse telephoneDAOResponse = telephoneRepository.update(telephoneDAORequest, newNumber);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }
}

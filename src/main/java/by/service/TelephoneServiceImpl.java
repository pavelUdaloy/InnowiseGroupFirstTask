package by.service;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;
import by.entity.dto.request.TelephoneDTORequest;
import by.entity.dto.response.TelephoneDTOResponse;
import by.mapper.TelephoneMapper.DTODAOMapper;
import by.repository.TelephoneRepository;
import by.repository.TelephoneRepositoryImpl;

import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository = new TelephoneRepositoryImpl();

    @Override
    public List<TelephoneDTOResponse> addAll(List<TelephoneDTORequest> telephoneDTORequests) {
        List<TelephoneDAORequest> telephoneDAORequests = DTODAOMapper.convertDTOReqsToDAOReqs(telephoneDTORequests);
        List<TelephoneDAOResponse> telephoneDAOResponses = telephoneRepository.addAll(telephoneDAORequests);
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDAOResponses);
    }

    @Override
    public TelephoneDTOResponse delete(TelephoneDTORequest telephoneDTORequest) {
        TelephoneDAORequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDAOResponse telephoneDAOResponse = telephoneRepository.delete(telephoneDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDTOResponse> deleteAll() {
        List<TelephoneDAOResponse> telephoneDAOResponses = telephoneRepository.deleteAll();
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDAOResponses);
    }

    @Override
    public TelephoneDTOResponse get(TelephoneDTORequest telephoneDTORequest) {
        TelephoneDAORequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDAOResponse telephoneDAOResponse = telephoneRepository.get(telephoneDAORequest);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }

    @Override
    public List<TelephoneDTOResponse> getByOwnerId(Integer ownerId) {
        List<TelephoneDAOResponse> telephoneDAOResponses = telephoneRepository.getByOwnerId(ownerId);
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDAOResponses);
    }

    @Override
    public List<TelephoneDTOResponse> getAll() {
        List<TelephoneDAOResponse> telephoneDAOResponses = telephoneRepository.getAll();
        return DTODAOMapper.convertDAORespsToDTOResps(telephoneDAOResponses);
    }

    @Override
    public TelephoneDTOResponse set(TelephoneDTORequest telephoneDTORequest, String newNumber) {
        TelephoneDAORequest telephoneDAORequest = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequest);
        TelephoneDAOResponse telephoneDAOResponse = telephoneRepository.set(telephoneDAORequest, newNumber);
        return DTODAOMapper.convertDAORespToDTOResp(telephoneDAOResponse);
    }
}

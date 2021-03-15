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
        List<TelephoneDAORequest> telephoneDAORequests = DTODAOMapper.convertDTOReqToDAOReq(telephoneDTORequests);
        List<TelephoneDAOResponse> add = telephoneRepository.add(telephoneDAORequests);
        return add;
    }

    @Override
    public TelephoneDTOResponse delete(TelephoneDTORequest telephoneDTORequest) {
        return telephoneRepository.delete(telephoneDTORequest);
    }

    @Override
    public List<TelephoneDTOResponse> deleteAll() {
        return telephoneRepository.deleteAll();
    }

    @Override
    public TelephoneDTOResponse get(TelephoneDTORequest telephoneDTORequest) {
        return telephoneRepository.get(telephoneDTORequest);
    }

    @Override
    public List<TelephoneDTOResponse> getAll() {
        return telephoneRepository.getAll();
    }

    @Override
    public TelephoneDTOResponse set(TelephoneDTORequest telephoneDTORequest, String newNumber) {
        return telephoneRepository.set(telephoneDTORequest, newNumber);
    }
}

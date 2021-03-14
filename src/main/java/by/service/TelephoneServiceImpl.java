package by.service;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dao.response.TelephoneDAOResponse;
import by.repository.TelephoneRepository;
import by.repository.TelephoneRepositoryImpl;

import java.util.List;

public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository = new TelephoneRepositoryImpl();

    @Override
    public TelephoneDAOResponse add(TelephoneDAORequest telephoneDAORequest) {
        return telephoneRepository.add(telephoneDAORequest);
    }

    @Override
    public TelephoneDAOResponse delete(TelephoneDAORequest telephoneDAORequest) {
        return telephoneRepository.delete(telephoneDAORequest);
    }

    @Override
    public List<TelephoneDAOResponse> deleteAll() {
        return telephoneRepository.deleteAll();
    }

    @Override
    public TelephoneDAOResponse get(TelephoneDAORequest telephoneDAORequest) {
        return telephoneRepository.get(telephoneDAORequest);
    }

    @Override
    public List<TelephoneDAOResponse> getAll() {
        return telephoneRepository.getAll();
    }

    @Override
    public TelephoneDAOResponse set(TelephoneDAORequest telephoneDAORequest, String newNumber) {
        return telephoneRepository.set(telephoneDAORequest, newNumber);
    }
}

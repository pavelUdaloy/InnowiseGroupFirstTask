package by.mapper.TelephoneMapper;

import by.entity.dao.request.TelephoneDAORequest;
import by.entity.dto.request.TelephoneDTORequest;

import java.util.ArrayList;
import java.util.List;

public class DTODAOMapper {
    public static List<TelephoneDAORequest> convertDTOReqToDAOReq(List<TelephoneDTORequest> telephoneDTORequestList) {
        List<TelephoneDAORequest> telephoneDAORequests = new ArrayList<>();
        for (TelephoneDTORequest telephoneDTORequest : telephoneDTORequestList) {
            TelephoneDAORequest telephoneDAORequest = new TelephoneDAORequest();
            telephoneDAORequest.setNumber(telephoneDTORequest.getNumber());
            telephoneDAORequest.setOwnerId(telephoneDTORequest.getOwnerId());
            telephoneDAORequests.add(telephoneDAORequest);
        }
        return telephoneDAORequests;
    }
}

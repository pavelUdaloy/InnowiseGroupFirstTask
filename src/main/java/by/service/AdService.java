package by.service;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.entity.dto.CarAdDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    AddAdResponse add(CarAdDto carAdDto, List<MultipartFile> files);

    DeleteAdResponse delete(Integer carAdId);

    GetAdResponse get(Integer carAdId);

    UpdateAdResponse update(UpdateAdRequest updateAdRequest);

    PaginationGetAdResponse getWithPagination(Integer size, Integer page);
}

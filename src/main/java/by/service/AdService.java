package by.service;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;

import java.util.List;

public interface AdService {
    AddAdResponse add(CarAdDto carAdDto, List<ImageDto> imageDtos) throws ConnectionWithDBLostException, NullQueryException;

    DeleteAdResponse delete(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException;

    GetAdResponse get(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException;

    UpdateAdResponse update(UpdateAdRequest updateAdRequest) throws ConnectionWithDBLostException, NullQueryException;

    PaginationGetAdResponse getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException, NullQueryException;
}

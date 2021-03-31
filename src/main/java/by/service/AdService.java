package by.service;

import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.servlet.responseentity.AddAdResponse;
import by.servlet.responseentity.DeleteAdResponse;
import by.servlet.responseentity.GetAdResponse;
import by.servlet.responseentity.PaginationGetAdResponse;
import by.servlet.responseentity.UpdateAdResponse;

import java.util.List;

public interface AdService {
    AddAdResponse add(CarAdDto carAdDto, List<ImageDto> imageDtos) throws ConnectionWithDBLostException, NullQueryException;

    DeleteAdResponse delete(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException;

    GetAdResponse get(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException;

    UpdateAdResponse update(Integer id, Integer age, String brand, String model, Integer engineSize, Integer enginePower, Integer mileage) throws ConnectionWithDBLostException, NullQueryException;

    PaginationGetAdResponse getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException, NullQueryException;
}

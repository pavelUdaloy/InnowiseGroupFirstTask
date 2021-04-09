package by.service;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.dao.EntityManagerProvider;
import by.entity.base.CarAd;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.entity.dto.UserDto;
import by.exception.DaoOperationException;
import by.mapper.CarAdMapper;
import by.mapper.UserMapper;
import by.repository.CarAdRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final CarAdRepository carAdRepository;
    private final UserService userService;
    private final CarAdMapper carAdMapper;
    private final UserMapper userMapper;

    public AdServiceImpl(CarAdRepository carAdRepository, UserService userService, CarAdMapper carAdMapper, UserMapper userMapper) {
        this.carAdRepository = carAdRepository;
        this.userService = userService;
        this.carAdMapper = carAdMapper;
        this.userMapper = userMapper;
    }

    @Override
    public AddAdResponse add(CarAdDto carAdDto, List<ImageDto> imageDtos) {
        UserDto userDto = userService.get(carAdDto.getOwnerId());
        CarAd carAd = carAdMapper.convertDtoToCarAd(carAdDto, userMapper.convertDtoToUser(userDto), imageDtos);

        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Integer id;
        try {
            id = carAdRepository.add(carAd);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        GetAdResponse getAdResponse = get(id);
        AddAdResponse addAdResponse = new AddAdResponse();
        addAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        addAdResponse.setImageDtos(getAdResponse.getImageDtos());
        return addAdResponse;
    }

    @Override
    public DeleteAdResponse delete(Integer carAdId) {
        GetAdResponse getAdResponse = get(carAdId);
        DeleteAdResponse deleteAdResponse = new DeleteAdResponse();
        deleteAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        deleteAdResponse.setImageDtos(getAdResponse.getImageDtos());

        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            carAdRepository.delete(carAdId);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }

        return deleteAdResponse;
    }

    @Override
    public GetAdResponse get(Integer carAdId) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        CarAd carAd;
        try {
            carAd = carAdRepository.get(carAdId);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return carAdMapper.convertCarAdToGetDto(carAd);
    }

    @Override
    public UpdateAdResponse update(UpdateAdRequest updateAdRequest) {
        updateAdRequest.setLastEditDate(LocalDateTime.now());
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            carAdRepository.update(updateAdRequest);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        GetAdResponse getAdResponse = get(updateAdRequest.getId());
        UpdateAdResponse updateAdResponse = new UpdateAdResponse();
        updateAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        return updateAdResponse;
    }

    @Override
    public PaginationGetAdResponse getWithPagination(Integer size, Integer page) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        List<CarAd> carAds;
        try {
            carAds = carAdRepository.getWithPagination(size, page);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        return carAdMapper.convertCarAdToGetPaginationDto(carAds);
    }
}

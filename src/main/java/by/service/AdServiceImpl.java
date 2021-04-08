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
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
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
    public AddAdResponse add(CarAdDto carAdDto, List<ImageDto> imageDtos) throws ConnectionWithDBLostException, NullQueryException {
        UserDto userDto = userService.get(carAdDto.getOwnerId());
        CarAd carAd = carAdMapper.convertDtoToCarAd(carAdDto, userMapper.convertDtoToUser(userDto), imageDtos);

        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Integer id = null;
        try {
            id = carAdRepository.add(carAd);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        if (id == null) {
            throw new NullQueryException();
        }
        GetAdResponse getAdResponse = get(id);
        AddAdResponse addAdResponse = new AddAdResponse();
        addAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        addAdResponse.setImageDtos(getAdResponse.getImageDtos());
        return addAdResponse;
    }

    @Override
    public DeleteAdResponse delete(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException {
        GetAdResponse getAdResponse = get(carAdId);
        DeleteAdResponse deleteAdResponse = new DeleteAdResponse();
        deleteAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        deleteAdResponse.setImageDtos(getAdResponse.getImageDtos());

        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            carAdRepository.delete(carAdId);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }

        return deleteAdResponse;
    }

    @Override
    public GetAdResponse get(Integer carAdId) throws ConnectionWithDBLostException, NullQueryException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        CarAd carAd;
        try {
            carAd = carAdRepository.get(carAdId);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        if (carAd == null) {
            throw new NullQueryException();
        }
        return carAdMapper.convertCarAdToGetDto(carAd);
    }

    @Override
    public UpdateAdResponse update(UpdateAdRequest updateAdRequest) throws ConnectionWithDBLostException, NullQueryException {
        updateAdRequest.setLastEditDate(LocalDateTime.now());
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        try {
            carAdRepository.update(updateAdRequest);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        GetAdResponse getAdResponse = get(updateAdRequest.getId());
        UpdateAdResponse updateAdResponse = new UpdateAdResponse();
        updateAdResponse.setCarAdDto(getAdResponse.getCarAdDto());
        return updateAdResponse;
    }

    @Override
    public PaginationGetAdResponse getWithPagination(Integer size, Integer page) throws ConnectionWithDBLostException, NullQueryException {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        List<CarAd> carAds;
        try {
            carAds = carAdRepository.getWithPagination(size, page);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new NullQueryException();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new ConnectionWithDBLostException();
        } finally {
            EntityManagerProvider.clear();
        }
        if (carAds == null) {
            throw new NullQueryException();
        }
        return carAdMapper.convertCarAdToGetPaginationDto(carAds);
    }
}

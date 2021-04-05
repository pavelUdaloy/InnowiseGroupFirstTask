package by.service;

import by.entity.base.CarAd;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.entity.dto.UserDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.mapper.CarAdMapper;
import by.mapper.UserMapper;
import by.provider.EntityManagerProvider;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;
import by.servlet.request.UpdateAdRequest;
import by.servlet.response.ad.AddAdResponse;
import by.servlet.response.ad.DeleteAdResponse;
import by.servlet.response.ad.GetAdResponse;
import by.servlet.response.ad.PaginationGetAdResponse;
import by.servlet.response.ad.UpdateAdResponse;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AdServiceImpl implements AdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    private final UserService userService = new UserServiceImpl();

    private final CarAdMapper carAdMapper = new CarAdMapper();
    private final UserMapper userMapper = new UserMapper();

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
    public UpdateAdResponse update(Integer id, Integer age, String brand, String model, Integer engineSize, Integer enginePower, Integer mileage) throws ConnectionWithDBLostException, NullQueryException {
        Timestamp lastEditDate = new Timestamp(new Date().getTime());
        UpdateAdRequest updateAdRequest = carAdMapper.convertDataToUpdateReq(id, age, brand, model, engineSize, enginePower, mileage, lastEditDate);
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
        GetAdResponse getAdResponse = get(id);
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

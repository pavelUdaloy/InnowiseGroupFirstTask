package by.service;

import by.db.EntityManagerProvider;
import by.entity.base.CarAd;
import by.entity.base.User;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.mapper.CarAdMapper;
import by.repository.CarAdRepository;
import by.repository.CarAdRepositoryImpl;
import by.servlet.requestentity.UpdateAdRequest;
import by.servlet.responseentity.AddAdResponse;
import by.servlet.responseentity.DeleteAdResponse;
import by.servlet.responseentity.GetAdResponse;
import by.servlet.responseentity.PaginationGetAdResponse;
import by.servlet.responseentity.UpdateAdResponse;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AdServiceImpl implements AdService {

    private final CarAdRepository carAdRepository = new CarAdRepositoryImpl();

    private final UserService userService = new UserServiceImpl();

    private final CarAdMapper carAdMapper = new CarAdMapper();

    @Override
    public AddAdResponse add(CarAdDto carAdDto, List<ImageDto> imageDtos) throws ConnectionWithDBLostException, NullQueryException {
        User user = userService.get(carAdDto.getOwnerId());

        CarAd carAd = carAdMapper.convertDtoToCarAd(carAdDto, user, imageDtos);

        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Integer id = null;
        try {
            id = carAdRepository.add(carAd);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (NullQueryException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
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
        if (carAd == null) throw new NullQueryException();
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
        if (carAds == null) throw new NullQueryException();
        return carAdMapper.convertCarAdToGetPaginationDto(carAds);
    }
}

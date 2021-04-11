package by.service;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.dao.EntityManagerProvider;
import by.entity.base.CarAd;
import by.entity.base.User;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.CustomRequestException;
import by.exception.DaoOperationException;
import by.mapper.CarAdMapper;
import by.mapper.UserMapper;
import by.repository.CarAdRepository;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.util.TextLabels.ANY_NOT_NUMERAL_SYMBOL;
import static by.util.TextLabels.DOT;
import static by.util.TextLabels.EMPTY;
import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.property;

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
    public AddAdResponse add(CarAdDto carAdDto, List<MultipartFile> files) {
        LocalDateTime timestamp = LocalDateTime.now();
        carAdDto.setCreationDate(timestamp);
        carAdDto.setLastEditDate(timestamp);

        User user = userMapper.convertDtoToUser(userService.get(carAdDto.getOwnerId()));
        List<ImageDto> imageDtos = saveFilesAndGetDto(files);
        CarAd carAd = carAdMapper.convertDtoToCarAd(carAdDto, user, imageDtos);

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

    private List<ImageDto> saveFilesAndGetDto(List<MultipartFile> files) {
        String basePath = property.getProperty(PROPERTIES_BASE_PATH);
        List<ImageDto> imageDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file instanceof CommonsMultipartFile) {
                CommonsMultipartFile cmf = (CommonsMultipartFile) file;
                FileItem item = cmf.getFileItem();

                String oldName = item.getName();
                String fileType = oldName.substring(oldName.lastIndexOf(DOT));
                LocalDateTime timestamp = LocalDateTime.now();
                String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);

                try {
                    item.write(new File(basePath + newName + fileType));
                } catch (Exception exception) {
                    throw new CustomRequestException();
                }

                ImageDto imageDto = new ImageDto();
                imageDto.setName(newName);
                imageDto.setFileFormat(fileType);
                imageDtos.add(imageDto);
            }
        }
        return imageDtos;
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

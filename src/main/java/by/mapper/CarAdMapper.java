package by.mapper;

import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.entity.base.CarAd;
import by.entity.base.Image;
import by.entity.base.User;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarAdMapper {

    public CarAd convertDtoToCarAd(CarAdDto carAdDto, User user, List<ImageDto> imageDtos) {
        CarAd carAd = convertDtoToCarAd(carAdDto);
        carAd.setUser(user);
        List<Image> images = new ArrayList<>();
        for (ImageDto imageDto : imageDtos) {
            Image image = new Image();
            image.setCarAd(carAd);
            image.setFileFormat(imageDto.getFileFormat());
            image.setName(imageDto.getName());
            images.add(image);
        }
        carAd.setImages(images);
        return carAd;
    }

    public CarAd convertDtoToCarAd(CarAdDto carAdDto) {
        CarAd carAd = new CarAd();

        carAd.setId(carAdDto.getId());
        carAd.setAge(carAdDto.getAge());
        carAd.setBrand(carAdDto.getBrand());
        carAd.setModel(carAdDto.getModel());
        carAd.setCondition(carAdDto.getCondition());
        carAd.setMileage(carAdDto.getMileage());
        carAd.setEngineSize(carAdDto.getEngineSize());
        carAd.setEnginePower(carAdDto.getEnginePower());
        carAd.setCreationDate(carAdDto.getCreationDate());
        carAd.setLastEditDate(carAdDto.getLastEditDate());

        return carAd;
    }

    public PaginationGetAdResponse convertCarAdToGetPaginationDto(List<CarAd> carAds) {
        PaginationGetAdResponse paginationGetAdResponse = new PaginationGetAdResponse();
        List<GetAdResponse> responses = new ArrayList<>();
        for (CarAd carAd : carAds) {
            responses.add(convertCarAdToGetDto(carAd));
        }
        paginationGetAdResponse.setResponses(responses);
        return paginationGetAdResponse;
    }

    public GetAdResponse convertCarAdToGetDto(CarAd carAd) {
        GetAdResponse getAdResponse = new GetAdResponse();

        List<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : carAd.getImages()) {
            ImageDto imageDto = new ImageDto();
            imageDto.setId(image.getId());
            imageDto.setName(image.getName());
            imageDto.setFileFormat(image.getFileFormat());
            imageDtos.add(imageDto);
        }
        getAdResponse.setImageDtos(imageDtos);

        CarAdDto carAdDto = new CarAdDto();
        carAdDto.setAge(carAd.getAge());
        carAdDto.setId(carAd.getId());
        carAdDto.setCondition(carAd.getCondition());
        carAdDto.setBrand(carAd.getBrand());
        carAdDto.setCreationDate(carAd.getCreationDate());
        carAdDto.setEnginePower(carAd.getEnginePower());
        carAdDto.setMileage(carAd.getMileage());
        carAdDto.setEngineSize(carAd.getEngineSize());
        carAdDto.setModel(carAd.getModel());
        carAdDto.setLastEditDate(carAd.getLastEditDate());
        carAdDto.setOwnerId(carAd.getUser().getId());
        getAdResponse.setCarAdDto(carAdDto);

        return getAdResponse;
    }
}

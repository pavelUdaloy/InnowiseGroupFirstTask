package by.mapper;

import by.controller.response.ad.GetImageResponse;
import by.entity.base.Image;
import by.entity.dto.ImageDto;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public GetImageResponse convertImageToDtoResponse(Image image) {
        GetImageResponse getImageResponse = new GetImageResponse();
        ImageDto imageDto = convertImageToImageDto(image);
        getImageResponse.setImageDto(imageDto);
        return getImageResponse;
    }

    public ImageDto convertImageToImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setName(image.getName());
        imageDto.setFileFormat(image.getFileFormat());
        imageDto.setId(image.getId());
        return imageDto;
    }
}

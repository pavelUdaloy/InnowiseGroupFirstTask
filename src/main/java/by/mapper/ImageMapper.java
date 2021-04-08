package by.mapper;

import by.controller.response.ad.GetImageResponse;
import by.entity.base.Image;
import by.entity.dto.ImageDto;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public GetImageResponse convertImageToDtoResponse(Image image) {
        GetImageResponse getImageResponse = new GetImageResponse();
        ImageDto imageDto = new ImageDto();
        imageDto.setName(image.getName());
        imageDto.setFileFormat(image.getFileFormat());
        imageDto.setId(image.getId());
        getImageResponse.setImageDto(imageDto);
        return getImageResponse;
    }
}

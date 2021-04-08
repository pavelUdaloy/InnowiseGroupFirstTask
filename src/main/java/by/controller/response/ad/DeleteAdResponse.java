package by.controller.response.ad;

import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeleteAdResponse {
    CarAdDto carAdDto;
    private List<ImageDto> imageDtos;
}

package by.servlet.responseentity;

import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAdResponse {
    private CarAdDto carAdDto = new CarAdDto();
    private List<ImageDto> imageDtos = new ArrayList<>();
}

package by.servlet.responseentity;

import by.entity.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetImageResponse {
    private ImageDto imageDto = new ImageDto();
}

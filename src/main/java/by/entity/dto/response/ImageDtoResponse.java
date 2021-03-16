package by.entity.dto.response;

import by.entity.abstractive.AbstractImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDtoResponse extends AbstractImage {
    private Integer ownerId;
    private Integer id;
}

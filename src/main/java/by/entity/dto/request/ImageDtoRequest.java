package by.entity.dto.request;

import by.entity.abstractive.AbstractImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDtoRequest extends AbstractImage {
    private Integer ownerId;
}

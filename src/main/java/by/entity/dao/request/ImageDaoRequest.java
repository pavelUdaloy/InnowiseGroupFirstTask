package by.entity.dao.request;

import by.entity.abstractive.AbstractImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDaoRequest extends AbstractImage {
    private Integer ownerId;
}

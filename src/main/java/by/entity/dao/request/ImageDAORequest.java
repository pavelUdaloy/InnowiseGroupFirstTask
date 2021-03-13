package by.entity.dao.request;

import by.entity.abstractive.AbstractImage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class ImageDAORequest extends AbstractImage {
    private Integer ownerId;
}

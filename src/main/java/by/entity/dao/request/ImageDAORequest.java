package by.entity.dao.request;

import by.entity.abstractive.AbstractImage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class ImageDAORequest extends AbstractImage {
    private Integer ownerId;
}

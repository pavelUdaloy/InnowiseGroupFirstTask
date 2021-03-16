package by.entity.dao.request;

import by.entity.abstractive.AbstractImage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class ImageDaoRequest extends AbstractImage {
    private Integer ownerId;
}

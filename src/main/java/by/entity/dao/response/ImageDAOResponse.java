package by.entity.dao.response;

import by.entity.abstractive.AbstractImage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageDAOResponse extends AbstractImage {
    private int id;
    private int ownerId;
}

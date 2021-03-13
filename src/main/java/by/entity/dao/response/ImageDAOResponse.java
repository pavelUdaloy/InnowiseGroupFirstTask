package by.entity.dao.response;

import by.entity.abstractive.AbstractImage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ImageDAOResponse extends AbstractImage {
    private int id;
    private int ownerId;
}

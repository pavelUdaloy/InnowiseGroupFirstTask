package by.entity.dao.response;

import by.entity.abstractive.AbstractImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDaoResponse extends AbstractImage {
    private int id;
    private int ownerId;
}

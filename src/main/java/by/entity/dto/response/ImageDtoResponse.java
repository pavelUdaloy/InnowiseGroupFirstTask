package by.entity.dto.response;

import by.entity.abstractive.AbstractImage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class ImageDtoResponse extends AbstractImage {
    private Integer ownerId;
    private Integer id;
}

package by.entity.dto.response;

import by.entity.abstractive.AbstractCarAd;
import by.entity.abstractive.AbstractImage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class CarAdDTOResponse extends AbstractCarAd {
    private List<AbstractImage> imageList= new ArrayList<>();
    private List<AbstractImage> telephoneList= new ArrayList<>();

    private Integer id;
    private Integer ownerId;
}

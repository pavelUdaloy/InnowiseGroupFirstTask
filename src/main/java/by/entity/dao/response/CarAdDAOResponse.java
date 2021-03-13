package by.entity.dao.response;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarAdDAOResponse extends AbstractCarAd {
    private Integer id;
    private Integer ownerId;
}

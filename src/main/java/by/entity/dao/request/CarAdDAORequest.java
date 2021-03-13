package by.entity.dao.request;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarAdDAORequest extends AbstractCarAd {
    private Integer ownerId;
}

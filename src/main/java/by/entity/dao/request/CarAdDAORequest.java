package by.entity.dao.request;

import by.entity.abstractive.AbstractCarAd;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CarAdDAORequest extends AbstractCarAd {
    private Integer ownerId;
}

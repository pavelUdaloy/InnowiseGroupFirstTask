package by.entity.dao.response;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CarAdDAOResponse extends AbstractCarAd {
    private Integer id;
    private Integer ownerId;
}

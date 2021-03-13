package by.entity.dao.request;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TelephoneDAORequest extends AbstractTelephone {
    private Integer ownerId;
}

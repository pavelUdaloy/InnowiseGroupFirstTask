package by.entity.dao.response;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TelephoneDAOResponse extends AbstractTelephone {
    private Integer id;
    private Integer ownerId;
}

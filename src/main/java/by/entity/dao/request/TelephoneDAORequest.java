package by.entity.dao.request;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class TelephoneDAORequest extends AbstractTelephone {
    private Integer ownerId;
}

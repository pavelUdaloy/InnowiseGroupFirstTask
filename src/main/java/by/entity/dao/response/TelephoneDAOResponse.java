package by.entity.dao.response;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class TelephoneDAOResponse extends AbstractTelephone {
    private Integer id;
    private Integer ownerId;
}

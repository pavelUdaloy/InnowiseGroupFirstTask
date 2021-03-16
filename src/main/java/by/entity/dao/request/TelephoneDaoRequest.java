package by.entity.dao.request;

import by.entity.abstractive.AbstractTelephone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelephoneDaoRequest extends AbstractTelephone {
    private Integer ownerId;
}

package by.entity.dao.response;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class TelephoneDaoResponse extends AbstractTelephone {
    private Integer id;
    private Integer ownerId;
}

package by.entity.dto.response;

import by.entity.abstractive.AbstractTelephone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class TelephoneDTOResponse extends AbstractTelephone {
    private Integer id;
    private Integer ownerId;
}

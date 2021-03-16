package by.entity.dto.response;

import by.entity.abstractive.AbstractTelephone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelephoneDtoResponse extends AbstractTelephone {
    private Integer id;
    private Integer ownerId;
}

package by.entity.dto.request;

import by.entity.abstractive.AbstractTelephone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelephoneDtoRequest extends AbstractTelephone {
    private Integer ownerId;
}

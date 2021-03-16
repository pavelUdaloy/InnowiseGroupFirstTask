package by.entity.dto.request;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class CarAdDtoRequest extends AbstractCarAd {
    private Integer id;
    private Integer ownerId;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
}
package by.entity.dto.request;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class CarAdDTORequest extends AbstractCarAd {
    private List<TelephoneDTORequest> telephones;
    private List<ImageDTORequest> images;
    private Integer ownerId;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
}

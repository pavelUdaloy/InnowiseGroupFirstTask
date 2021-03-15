package by.entity.dto.response;

import by.entity.abstractive.AbstractCarAd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class CarAdDTOResponse extends AbstractCarAd {
    private List<TelephoneDTOResponse> telephoneList= new ArrayList<>();
    private Integer imageQuantity;

    private Integer id;
    private Integer ownerId;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
}

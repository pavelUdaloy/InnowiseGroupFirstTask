package by.entity.dto.response;

import by.entity.abstractive.AbstractCarAd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarAdDtoResponse extends AbstractCarAd {
    private List<TelephoneDtoResponse> telephoneList = new ArrayList<>();
    private Integer imageQuantity;

    private Integer id;
    private Integer ownerId;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
}

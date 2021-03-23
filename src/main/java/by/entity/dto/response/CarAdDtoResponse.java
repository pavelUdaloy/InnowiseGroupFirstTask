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
//@Entity
//@Table(name = "car_ads")
public class CarAdDtoResponse extends AbstractCarAd {
    private List<TelephoneDtoResponse> telephoneList = new ArrayList<>();
    private Integer imageQuantity;

    //    @Id
//    @Column(name = "id", unique = true)
    private Integer id;
    //    @Column(name = "owner_id")
    private Integer ownerId;
    //    @Column(name = "creation_date")
    private Timestamp creationDate;
    //    @Column(name = "last_edit_date")
    private Timestamp lastEditDate;
}

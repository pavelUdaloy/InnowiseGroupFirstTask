package by.entity.dao.response;

import by.entity.abstractive.AbstractCarAd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CarAdDaoResponse extends AbstractCarAd {
    private Integer id;
    private Integer ownerId;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
}

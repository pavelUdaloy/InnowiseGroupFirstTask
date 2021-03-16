package by.entity.dao.response;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class UserDaoResponse extends AbstractUser {
    private Integer id;
}

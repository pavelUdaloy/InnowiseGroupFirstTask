package by.entity.dao.response;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDAOResponse extends AbstractUser {
    private Integer id;
}

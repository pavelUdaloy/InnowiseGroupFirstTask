package by.entity.dao.response;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDAOResponse extends AbstractUser {
    private Integer id;
}

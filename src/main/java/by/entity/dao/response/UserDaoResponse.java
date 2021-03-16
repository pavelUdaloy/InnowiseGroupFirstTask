package by.entity.dao.response;

import by.entity.abstractive.AbstractUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDaoResponse extends AbstractUser {
    private Integer id;
}

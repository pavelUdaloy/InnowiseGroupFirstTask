package by.entity.dto.response;

import by.entity.abstractive.AbstractUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoResponse extends AbstractUser {
    private Integer id;
}

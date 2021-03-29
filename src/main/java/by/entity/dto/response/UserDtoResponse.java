package by.entity.dto.response;

import by.entity.base.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoResponse extends User {
    private Integer id;
}

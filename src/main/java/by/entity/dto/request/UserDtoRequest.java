package by.entity.dto.request;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data()
@ToString(callSuper = true)
public class UserDtoRequest extends AbstractUser {
}

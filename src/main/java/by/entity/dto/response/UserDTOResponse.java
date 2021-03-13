package by.entity.dto.response;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDTOResponse extends AbstractUser {
}

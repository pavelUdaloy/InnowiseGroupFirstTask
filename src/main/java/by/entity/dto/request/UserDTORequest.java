package by.entity.dto.request;

import by.entity.abstractive.AbstractUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDTORequest extends AbstractUser {
}

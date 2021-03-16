package by.entity.abstractive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUser {
    private String email;
    private String firstName;
    private String lastName;
}

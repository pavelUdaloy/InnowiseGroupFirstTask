package by.entity.abstractive;

import lombok.Data;

@Data
public abstract class AbstractUser {
    private String email;
    private String firstName;
    private String lastName;
}

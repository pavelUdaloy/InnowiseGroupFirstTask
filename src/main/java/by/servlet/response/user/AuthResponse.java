package by.servlet.response.user;

import by.entity.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
    private UserDto userDto;
    private Boolean auth;
}

package by.mapper;

import by.entity.base.User;
import by.entity.dto.UserDto;

public class UserMapper {
    public User convertDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        return user;
    }

    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        return userDto;
    }
}

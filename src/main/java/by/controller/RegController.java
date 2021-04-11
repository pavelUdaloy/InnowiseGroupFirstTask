package by.controller;

import by.entity.dto.UserDto;
import by.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg", consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegController {
    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto post(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }
}
package by.controller;

import by.entity.dto.UserDto;
import by.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg")
public class RegController {
    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<UserDto> post(@RequestBody UserDto userDto) {
        UserDto addedUser = userService.add(userDto);
        return new ResponseEntity<>(addedUser, HttpStatus.ACCEPTED);
    }
}
package by.controller;

import by.controller.response.user.AuthResponse;
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
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<AuthResponse> post(@RequestBody UserDto userDto) {
        AuthResponse auth = userService.auth(userDto);
        return new ResponseEntity<>(auth, HttpStatus.ACCEPTED);
    }
}
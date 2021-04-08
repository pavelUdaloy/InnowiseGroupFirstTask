package by.controller;

import by.controller.response.user.AuthResponse;
import by.controller.util.ErrorUtils;
import by.entity.dto.UserDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
import by.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final ErrorUtils errorUtils;

    public AuthController(UserService userService, ErrorUtils errorUtils) {
        this.userService = userService;
        this.errorUtils = errorUtils;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity post(@RequestBody UserDto userDto) {
        try {
            AuthResponse auth = userService.auth(userDto);
            return new ResponseEntity(auth, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
    }
}
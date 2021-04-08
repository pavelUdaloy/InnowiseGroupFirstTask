package by.controller;

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
@RequestMapping(path = "/reg")
public class RegController {
    private final UserService userService;
    private final ErrorUtils errorUtils;

    public RegController(UserService userService, ErrorUtils errorUtils) {
        this.userService = userService;
        this.errorUtils = errorUtils;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity post(@RequestBody UserDto userDto) {
        try {
            UserDto addedUser = userService.add(userDto);
            return new ResponseEntity(addedUser, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
    }
}
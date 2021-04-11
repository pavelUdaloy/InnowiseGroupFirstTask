package by.controller;

import by.controller.response.user.LogoutResponse;
import by.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/logout")
public class LogoutController {

    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public LogoutResponse logout() {
        return userService.logout();
    }
}
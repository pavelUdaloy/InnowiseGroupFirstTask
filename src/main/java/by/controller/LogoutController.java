package by.controller;

import by.controller.response.user.LogoutResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/logout")
public class LogoutController {
    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<LogoutResponse> post() {
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setLogout(true);
        return new ResponseEntity<>(logoutResponse, HttpStatus.ACCEPTED);
    }
}
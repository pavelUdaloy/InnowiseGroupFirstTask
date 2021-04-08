package by.controller;

import by.controller.response.user.LogoutResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {
    @PostMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity post() {
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setLogout(true);
        return new ResponseEntity(logoutResponse, HttpStatus.ACCEPTED);
    }
}
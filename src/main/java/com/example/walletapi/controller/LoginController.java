package com.example.walletapi.controller;

import com.example.walletapi.model.User;
import com.example.walletapi.service.LoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class LoginController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid User user) {
        LOGGER.info("Started to signUp");
        return loginService.signUp(user);
    }
}

package com.example.walletapi.controller;

import com.example.walletapi.model.LoginModel;
import com.example.walletapi.model.User;
import com.example.walletapi.service.LoginService;
import com.example.walletapi.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid User user) {
        String status = loginService.signUp(user);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginModel loginModel) {
        ApiResponse apiResponse = loginService.login(loginModel);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse.getMessage());
    }
}

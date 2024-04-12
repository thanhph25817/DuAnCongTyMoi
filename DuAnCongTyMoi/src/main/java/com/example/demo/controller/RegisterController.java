package com.example.demo.controller;

import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterRespone;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final UserServiceImpl service;

    public RegisterController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/add/register")
    public ResponseEntity<RegisterRespone> addRegister(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(service.addAccount(request), HttpStatus.OK);
    }
}

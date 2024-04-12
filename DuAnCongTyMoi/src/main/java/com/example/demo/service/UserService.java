package com.example.demo.service;

import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterRespone;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterRespone addAccount(RegisterRequest request);
}

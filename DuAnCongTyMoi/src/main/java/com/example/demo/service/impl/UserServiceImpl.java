package com.example.demo.service.impl;

import com.example.demo.Validator;
import com.example.demo.entity.User;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.BadRequestAlertException;
import com.example.demo.model.AppConfigMapper;
import com.example.demo.model.RegisterRequest;
import com.example.demo.model.RegisterRespone;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppConfigMapper mapper;

    public UserServiceImpl(UserRepository userRepository, AppConfigMapper appConfigMapper, AppConfigMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;

    }


    @Override
    public RegisterRespone addAccount(RegisterRequest request) {

        Optional<User> findUser = userRepository.findAllByEmail(request.getEmail());
        if (findUser.isPresent()) {
            throw new RuntimeException("email da ton tai");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setEmail(request.getEmailAgain());
        user.setPassWord(request.getPassWord());
        user.setDate(request.getDate());
        user.setGioiTinh(request.getGioiTinh());
        if (!request.getEmail().equals(request.getEmailAgain())) {
            throw new RuntimeException("Emails must match.");
        }
        userRepository.save(user);
        RegisterRespone respone = mapper.modelMapper().map(user, RegisterRespone.class);
        return respone;
    }

    private void validateIdentity(RegisterRequest registerRequest) {
        if (Validator.ValidateEmail(registerRequest.getEmail())) {
            throw new BadRequestAlertException(ErrorCode.EMS1001);
        }
        if (Validator.isNull(registerRequest.getEmail())) {
            throw new BadRequestAlertException(ErrorCode.EMS1002);
        }
    }
}

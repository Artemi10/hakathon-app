package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.LogInDto;
import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.exception.AuthException;
import com.itamnesia.bhcrud.mapper.UserMapper;
import com.itamnesia.bhcrud.model.user.Role;
import com.itamnesia.bhcrud.model.user.User;
import com.itamnesia.bhcrud.repository.UserRepository;
import com.itamnesia.bhcrud.service.ConfirmationSender;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements com.itamnesia.bhcrud.service.UserAuthService {

    private final UserRepository userRepository;
    private final ConfirmationSender confirmationSender;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User logIn(LogInDto logInDto) {
        User user = userRepository.findByPhoneNumber(logInDto.getPhoneNumber())
                .orElseThrow(() -> new AuthException("Credentials are invalid"));

       if (!passwordEncoder.matches(logInDto.getPassword(), user.getPassword())) {
           throw new AuthException("Credentials are invalid");
       }

       return user;
    }

    @Override
    public User signUp(SignUpDto signUpDto) {
        if (userRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())) {
            throw new AuthException("User has already been registered");
        }

        User user = userMapper.toModel(signUpDto);
        String code = RandomStringUtils.randomNumeric(4);
        user.setCode(passwordEncoder.encode("4444"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //confirmationSender.send(user.getPhoneNumber(), code);
        return userRepository.save(user);
    }

    @Override
    public User confirm(String phoneNumber, CodeDto codeDto) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AuthException("Credentials are invalid"));

        if (passwordEncoder.matches(codeDto.getCode(), user.getCode())) {
            user.setCode(null);
            user.setRole(Role.ACTIVE);
        } else {
            throw new AuthException("Code is incorrect");
        }

        return userRepository.save(user);
    }
}

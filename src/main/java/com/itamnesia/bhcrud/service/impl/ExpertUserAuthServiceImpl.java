package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.LogInDto;
import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.exception.AuthException;
import com.itamnesia.bhcrud.mapper.ExpertMapper;
import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.model.user.Role;
import com.itamnesia.bhcrud.repository.ExpertRepository;
import com.itamnesia.bhcrud.service.ExpertAuthService;
import com.itamnesia.bhcrud.service.ConfirmationSender;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertUserAuthServiceImpl implements ExpertAuthService {

    private final ExpertRepository expertRepository;
    private final ConfirmationSender confirmationSender;
    private final PasswordEncoder passwordEncoder;
    private final ExpertMapper expertMapper;

    @Override
    public Expert logIn(LogInDto logInDto) {
        Expert expert = expertRepository.findByPhoneNumber(logInDto.getPhoneNumber())
                .orElseThrow(() -> new AuthException("Credentials are invalid"));

        if (!passwordEncoder.matches(logInDto.getPassword(), expert.getPassword())) {
            throw new AuthException("Credentials are invalid");
        }

        return expert;
    }

    @Override
    public Expert signUp(SignUpDto signUpDto) {
        if (expertRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())) {
            throw new AuthException("User has already been registered");
        }

        Expert expert = expertMapper.toModel(signUpDto);
        String code = RandomStringUtils.randomNumeric(4);
        expert.setCode(passwordEncoder.encode("4444"));
        expert.setPassword(passwordEncoder.encode(expert.getPassword()));

        //confirmationSender.send(user.getPhoneNumber(), code);
        return expertRepository.save(expert);
    }

    @Override
    public Expert confirm(String phoneNumber, CodeDto codeDto) {
        Expert expert = expertRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AuthException("Credentials are invalid"));

        if (passwordEncoder.matches(codeDto.getCode(), expert.getCode())) {
            expert.setCode(null);
            expert.setRole(Role.EXPERT);
        } else {
            throw new AuthException("Code is incorrect");
        }

        return expertRepository.save(expert);
    }
}

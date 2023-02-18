package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.LogInDto;
import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.model.user.User;

public interface UserAuthService {

    User logIn(LogInDto logInDto);

    User signUp(SignUpDto signUpDto);

    User confirm(String phoneNumber, CodeDto codeDto);
}

package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.LogInDto;
import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.model.user.Expert;

public interface ExpertAuthService {

    Expert logIn(LogInDto logInDto);

    Expert signUp(SignUpDto signUpDto);

    Expert confirm(String phoneNumber, CodeDto codeDto);
}

package com.itamnesia.bhcrud.controller.auth;

import com.itamnesia.bhcrud.dto.auth.LogInDto;
import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.dto.auth.TokenDto;
import com.itamnesia.bhcrud.model.user.User;
import com.itamnesia.bhcrud.security.token.AccessTokenService;
import com.itamnesia.bhcrud.service.UserAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final AccessTokenService accessTokenService;

    @PostMapping(value = "/log-in")
    @ApiOperation("Log in an existed user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token with ACTIVE ROLE", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Email and password combination is incorrect")
    })
    public TokenDto logIn(@Valid @RequestBody LogInDto logInDto) {
        User user =  userAuthService.logIn(logInDto);
        String token = accessTokenService.createToken(user);
        return TokenDto.builder()
                .accessToken(token)
                .build();
    }

    @PostMapping("/sign-up")
    @ApiOperation("Log in an existed user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token with UNCONFIRMED ROLE", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Email and password combination is incorrect")
    })
    public TokenDto signUp(@Valid @RequestBody SignUpDto signUpDto) {
        User user = userAuthService.signUp(signUpDto);
        String token = accessTokenService.createToken(user);
        return TokenDto.builder()
                .accessToken(token)
                .build();
    }
}

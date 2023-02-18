package com.itamnesia.bhcrud.controller.auth;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.TokenDto;
import com.itamnesia.bhcrud.model.user.User;
import com.itamnesia.bhcrud.security.details.UserPrincipal;
import com.itamnesia.bhcrud.security.token.AccessTokenService;
import com.itamnesia.bhcrud.service.UserAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/confirmation")
public class UserConfirmationController {

    private final UserAuthService userAuthService;
    private final AccessTokenService accessTokenService;

    @PostMapping
    @ApiOperation("Confirm user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token with ACTIVE ROLE", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Email and password combination is incorrect")
    })
    public TokenDto confirm(
            @RequestBody CodeDto codeDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        User user = userAuthService.confirm(userPrincipal.getUsername(), codeDto);
        String accessToken = accessTokenService.createToken(user);
        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }
}

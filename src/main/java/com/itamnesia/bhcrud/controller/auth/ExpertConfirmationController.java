package com.itamnesia.bhcrud.controller.auth;

import com.itamnesia.bhcrud.dto.auth.CodeDto;
import com.itamnesia.bhcrud.dto.auth.TokenDto;
import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.security.details.UserPrincipal;
import com.itamnesia.bhcrud.security.token.AccessTokenService;
import com.itamnesia.bhcrud.service.ExpertAuthService;
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
@RequestMapping("/api/v1/expert/confirmation")
public class ExpertConfirmationController {

    private final ExpertAuthService expertAuthService;
    private final AccessTokenService accessTokenService;

    @PostMapping
    @ApiOperation("Confirm expert")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Token with EXPERT ROLE", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Email and password combination is incorrect")
    })
    public TokenDto confirm(
            @RequestBody CodeDto codeDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Expert expert = expertAuthService.confirm(userPrincipal.getUsername(), codeDto);
        String accessToken = accessTokenService.createToken(expert);
        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

}

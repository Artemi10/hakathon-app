package com.itamnesia.bhcrud.security.token;

import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.model.user.User;

public interface AccessTokenService {
    String createToken(User user);

    String createToken(Expert expert);

    String getCredentials(String accessToken);

    String getRole(String accessToken);

    boolean isValid(String accessToken);

}

package com.itamnesia.bhcrud.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AuthException extends ResponseStatusException {

    private final String message;

    public AuthException(String message) {
        super(HttpStatus.UNAUTHORIZED);
        this.message = message;
    }

}

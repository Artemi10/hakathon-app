package com.itamnesia.bhcrud.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UnavailableServiceException extends ResponseStatusException {

    private final String message;

    public UnavailableServiceException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE);
        this.message = message;
    }

}

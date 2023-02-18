package com.itamnesia.bhcrud.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class EntityNotFoundException extends ResponseStatusException  {

    private final String message;

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND);
        this.message = message;
    }

    public static EntityNotFoundException userNotFoundException() {
        return new EntityNotFoundException("User not found");
    }

    public static EntityNotFoundException announcementReportNotFoundException() {
        return new EntityNotFoundException("Announcement report not found");
    }

    public static EntityNotFoundException expertNotFoundException() {
        return new EntityNotFoundException("Expert not found");
    }

}

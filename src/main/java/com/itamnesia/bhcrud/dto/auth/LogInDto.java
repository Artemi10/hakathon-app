package com.itamnesia.bhcrud.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
}

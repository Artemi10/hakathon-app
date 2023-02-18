package com.itamnesia.bhcrud.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
@Configuration
@ConfigurationProperties("sms.credentials")
public class SmsCredentials {
    @NotBlank(message = "Add sms traffic login")
    @NotNull(message = "Add sms traffic login")
    private String login;
    @NotBlank(message = "Add sms traffic password")
    @NotNull(message = "Add sms traffic password")
    private String password;
    @NotBlank(message = "Add sms traffic originator")
    @NotNull(message = "Add sms traffic originator")
    private String originator;
}
